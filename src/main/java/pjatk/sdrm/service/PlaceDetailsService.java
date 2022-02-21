package pjatk.sdrm.service;

import org.springframework.stereotype.Service;
import pjatk.sdrm.exception.IncorrectHourException;
import pjatk.sdrm.exception.PlaceNotFoundException;
import pjatk.sdrm.exception.SpaceNotFoundException;
import pjatk.sdrm.exception.WrongReservationDateException;
import pjatk.sdrm.mapper.CoordsMapper;
import pjatk.sdrm.mapper.ReservationMapper;
import pjatk.sdrm.mapper.TimeSlotMapper;
import pjatk.sdrm.model.dto.PlaceDtoRequest;
import pjatk.sdrm.model.dto.PlaceDtoResponse;
import pjatk.sdrm.model.dto.timeslot.TimeSlotDtoDetailed;
import pjatk.sdrm.model.entity.*;
import pjatk.sdrm.repository.PlaceDetailsRepository;
import pjatk.sdrm.repository.SpaceCoordinatesRepository;
import pjatk.sdrm.repository.SpaceDetailRepository;
import pjatk.sdrm.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class PlaceDetailsService {

    private final PlaceDetailsRepository placeDetailsRepository;
    private final UserRepository userRepository;
    private final SpaceDetailRepository spaceDetailRepository;
    private final SpaceCoordinatesRepository spaceCoordinatesRepository;

    public PlaceDetailsService(PlaceDetailsRepository placeDetailsRepository, UserRepository userRepository, SpaceDetailRepository spaceDetailRepository, SpaceCoordinatesRepository spaceCoordinatesRepository) {
        this.placeDetailsRepository = placeDetailsRepository;
        this.userRepository = userRepository;
        this.spaceDetailRepository = spaceDetailRepository;
        this.spaceCoordinatesRepository = spaceCoordinatesRepository;
    }

    public List<PlaceDtoResponse> findAllReservations() {
        return placeDetailsRepository.findAll()
                .stream()
                .map(ReservationMapper::mapToReservationDtoResponse)
                .collect(Collectors.toList());
    }

    public PlaceDtoResponse findById(Long id) throws PlaceNotFoundException {
        return placeDetailsRepository.findById(id)
                .map(ReservationMapper::mapToReservationDtoResponse)
                .orElseThrow(() -> new PlaceNotFoundException("Place not found"));
    }

    public TimeSlotDtoDetailed findTimeSlot(Long resId, Long timeSlotId) {
        return TimeSlotMapper.mapToTimeSlotDtoDetailed(placeDetailsRepository.findById(resId).get().getTimeSlots().stream()
                .filter(x -> Objects.equals(x.getId(), timeSlotId)).findFirst().get());
    }

    public void deleteById(Long idReservation) {
        placeDetailsRepository.deleteById(idReservation);
    }

    public PlaceDtoResponse save(PlaceDtoRequest newReservation) throws IncorrectHourException, WrongReservationDateException, SpaceNotFoundException {
        Optional<SpaceDetail> spaceDetailOptional = spaceDetailRepository.findById(newReservation.getReservationSpaceId());
        if (spaceDetailOptional.isEmpty())
            throw new SpaceNotFoundException("No space found in system!");
        SpaceDetail spaceDetail = spaceDetailOptional.get();
        Date resStartDate = newReservation.getReservationStartDate();
        Date resEndDate = newReservation.getReservationEndDate();
        if (resEndDate.before(resStartDate)) {
            throw new WrongReservationDateException("Reservation end date can not be before reservation start date");
        }

        List<PlaceDetails> placeDetailsList = placeDetailsRepository.findAll()
                .stream()
                .filter(res -> res.getReservationStartDate().equals(newReservation.getReservationStartDate()))
                .collect(Collectors.toList());

        if (!placeDetailsList.isEmpty()) {
            throw new IncorrectHourException("This hour is taken.");
        }

        Long newReservationUserData = newReservation.getUserData();
        Optional<UserData> userDataOptional = userRepository.findById(newReservationUserData);
        if (userDataOptional.isEmpty()) {
            throw new EntityNotFoundException(String.format("User with id %s not found", newReservationUserData));
        } else {
            UserData userData = userDataOptional.get();
            PlaceDetails placeDetails = ReservationMapper.mapToReservationDtoEntity(newReservation, userData, null,spaceDetail);
            PlaceDetails newRes = placeDetailsRepository.save(placeDetails);
            SpaceCoordinates coordinates = spaceCoordinatesRepository.save(CoordsMapper.mapToSpaceCoordinates(newReservation.getCoords(),newRes));
            List<TimeSlot> newSlots = timeSlots(newReservation.getReservationStartDate(), newReservation.getReservationEndDate(), newRes);
            newRes.setTimeSlots(newSlots);
            newRes.setSpaceCoordinates(coordinates);
            PlaceDetails updatedRes = placeDetailsRepository.save(newRes);
            return ReservationMapper.mapToReservationDtoResponse(updatedRes);
        }
    }

    private List<TimeSlot> timeSlots(Date start, Date endDate, PlaceDetails reservation){
        ArrayList<TimeSlot> arr = new ArrayList<>();
        long dif = start.getTime();
        Date slot;
        double iter = TimeUnit.DAYS.convert(endDate.getTime() - start.getTime(), TimeUnit.MILLISECONDS)+1;
        Calendar startC = Calendar.getInstance();
        startC.setTime(start);
        for (int i = 0; i < iter; i++) {
            var baseStartHour = startC.get(Calendar.HOUR_OF_DAY);
            Calendar endC= Calendar.getInstance();
            endC.setTime(endDate);
            while (startC.get(Calendar.HOUR_OF_DAY) < endC.get(Calendar.HOUR_OF_DAY)) {
                slot = new Date(dif);
                arr.add(new TimeSlot(slot,false,reservation));
                dif += 3600000;
                startC.add(Calendar.HOUR_OF_DAY,1);
            }
            startC.set(Calendar.HOUR_OF_DAY,baseStartHour);
            startC.add(Calendar.DATE, 1);
            slot = startC.getTime();
            dif = slot.getTime();
        }
        return arr;
    }
}