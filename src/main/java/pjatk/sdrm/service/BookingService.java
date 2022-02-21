package pjatk.sdrm.service;

import org.springframework.stereotype.Service;
import pjatk.sdrm.exception.PlaceNotFoundException;
import pjatk.sdrm.exception.TimeslotNotFoundException;
import pjatk.sdrm.exception.UserNotFoundException;
import pjatk.sdrm.mailer.MailerComponent;
import pjatk.sdrm.mapper.TimeSlotMapper;
import pjatk.sdrm.model.dto.BookingDetails;
import pjatk.sdrm.model.dto.BookingStatus;
import pjatk.sdrm.model.dto.timeslot.BookedTimeSlotData;
import pjatk.sdrm.model.entity.TimeSlot;
import pjatk.sdrm.model.entity.UserData;
import pjatk.sdrm.repository.PlaceDetailsRepository;
import pjatk.sdrm.repository.TimeSlotRepository;
import pjatk.sdrm.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final TimeSlotRepository timeSlotRepository;
    private final PlaceDetailsRepository placeDetailsRepository;
    private final UserRepository userRepository;
    private final MailerComponent mailerComponent;

    public BookingService(TimeSlotRepository timeSlotRepository, PlaceDetailsRepository placeDetailsRepository, UserRepository userRepository, MailerComponent mailerComponent) {
        this.timeSlotRepository = timeSlotRepository;
        this.placeDetailsRepository = placeDetailsRepository;
        this.userRepository = userRepository;
        this.mailerComponent = mailerComponent;
    }

    public BookingStatus addBooking(BookingDetails bookingDetails) throws
            UserNotFoundException, PlaceNotFoundException, TimeslotNotFoundException {
        UserData userData = userRepository.findById(bookingDetails.getUserId())
                .orElseThrow(() -> new UserNotFoundException("No user with provided id in system"));
        TimeSlot timeslot =
                placeDetailsRepository.findById(bookingDetails.getPlaceId())
                        .orElseThrow(() -> new PlaceNotFoundException("No place with provided id in system"))
                        .getTimeSlots()
                        .stream().filter(x -> x.getId().equals(bookingDetails.getTimeSlotId()) && !x.isBooked())
                        .map(x -> TimeSlot.update(x, bookingDetails, userData)).findFirst()
                        .orElseThrow(() -> new TimeslotNotFoundException("No available timeslots for provided data"));
        timeSlotRepository.saveAndFlush(timeslot);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String beginDate = sdf.format(timeslot.getReservation().getReservationStartDate());
        String endDate = sdf.format(timeslot.getReservation().getReservationEndDate());

        String template = "Hi " + userData.getUserName() + "\n"
                + "You have made reservation, which will begin at  " + beginDate
                + " and will finish at " + endDate + "\n"
                + "Thank you and see you at the restaurant!";

        mailerComponent.sendConfirmationMail(userData.getUserEmail(), template);

        return new BookingStatus("You have successfully book your place!", timeslot.getTimeSlot());
    }

    public List<BookedTimeSlotData> getAllBookedSlotsByUserId(Long userId) {
        Optional<UserData> userDataOptional = userRepository.findById(userId);
        if (userDataOptional.isPresent()) {
            UserData userData = userDataOptional.get();
            return timeSlotRepository.findTimeSlotByUserDataAndIsBooked(userData, true).stream()
                    .map(TimeSlotMapper::mapToBookedTimeSlotData).collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException(String.valueOf(userId));
        }
    }

    public void deleteReservationById(Long userId, Long timeSlotId) {
        Optional<UserData> userDataOptional = userRepository.findById(userId);
        if (userDataOptional.isPresent()) {
            UserData userData = userDataOptional.get();
            List<TimeSlot> listTimeSlot = timeSlotRepository.findTimeSlotByUserDataAndIsBooked(userData, true);
            for (TimeSlot ts : listTimeSlot) {
                if (ts.getReservation().getPlaceId().equals(timeSlotId)) {
                    ts.setBooked(false);
                    ts.setBookingCancelTime(new Date(System.currentTimeMillis()));
                    ts.setPersonalRequest("Cancelled reservation from " + ts.getBookingCreationTime());
                    ts.setBookingCreationTime(null);

                    //new slot with same hour interval and exact same placeId as deleted one
                    TimeSlot timeSlotToReplace = new TimeSlot();
                    timeSlotToReplace.setUserData(ts.getUserData());
                    timeSlotToReplace.setBooked(false);
                    timeSlotToReplace.setTimeSlot(ts.getTimeSlot());
                    timeSlotToReplace.setReservation(ts.getReservation());

                    timeSlotRepository.saveAndFlush(ts);
                    timeSlotRepository.saveAndFlush(timeSlotToReplace);
                }
            }
        } else {
            throw new EntityNotFoundException(String.valueOf(userId));
        }
    }

    public List<BookedTimeSlotData> getAllCanceledReservation(Long userId) {
        Optional<UserData> userDataOptional = userRepository.findById(userId);
        if (userDataOptional.isPresent()) {
            UserData userData = userDataOptional.get();
            return timeSlotRepository.findTimeSlotByUserDataAndIsBookedAndBookingCancelTimeIsNotNull(userData, false).stream()
                    .map(TimeSlotMapper::mapToBookedTimeSlotData).collect(Collectors.toList());
        } else {
            throw new EntityNotFoundException(String.valueOf(userId));
        }
    }
}