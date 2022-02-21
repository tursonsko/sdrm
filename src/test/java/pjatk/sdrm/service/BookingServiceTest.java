package pjatk.sdrm.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pjatk.sdrm.exception.TimeslotNotFoundException;
import pjatk.sdrm.exception.UserNotFoundException;
import pjatk.sdrm.mapper.TimeSlotMapper;
import pjatk.sdrm.model.dto.BookingDetails;
import pjatk.sdrm.model.dto.timeslot.BookedTimeSlotData;
import pjatk.sdrm.model.entity.PlaceDetails;
import pjatk.sdrm.model.entity.TimeSlot;
import pjatk.sdrm.model.entity.UserData;
import pjatk.sdrm.repository.PlaceDetailsRepository;
import pjatk.sdrm.repository.TimeSlotRepository;
import pjatk.sdrm.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

    @Mock
    private PlaceDetailsRepository placeDetailsRepository;

    @Mock
    private TimeSlotRepository timeSlotRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookingDetails bookingDetails;

    @Mock
    private UserData userData;

    @Mock
    private TimeSlot timeSlot;

    @Mock
    private BookedTimeSlotData bookedTimeSlotData;

    @Mock
    private TimeSlotMapper timeSlotMapper;

    @InjectMocks
    private BookingService bookingService;

    @Test
    public void testGetAllBookedSlotsByUserIdCorrectlyReturn() {

        Date createDate = new Date();
        Date timeSlotData = new Date();

        PlaceDetails placeDetails = new PlaceDetails();
        placeDetails.setPlaceId(1L);

        Long userId = 1L;
        UserData userData = new UserData();
        userData.setId(userId);

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setId(1L);
        timeSlot.setReservation(placeDetails);
        timeSlot.setTimeSlot(timeSlotData);
        timeSlot.setBookingCreationTime(createDate);
        timeSlot.setPersonalRequest("something");

        List<TimeSlot> timeSlotList = new ArrayList<>();
        timeSlotList.add(timeSlot);

        List<BookedTimeSlotData> expectedList = new ArrayList<>();
        BookedTimeSlotData bookedTimeSlotData = new BookedTimeSlotData(1L, 1L, timeSlotData, createDate, "something");
        expectedList.add(bookedTimeSlotData);

        when(userRepository.findById(userId)).thenReturn(Optional.of(new UserData()));
        when(timeSlotRepository.findTimeSlotByUserDataAndIsBooked(userData, true)).thenReturn(timeSlotList);

        List<BookedTimeSlotData> resultList = bookingService.getAllBookedSlotsByUserId(userId);

        assertEquals(resultList, expectedList);
    }

    @Test
    public void testGetAllBookedSlotsByUserIdFailUserIsNotPresent() {

        UserData userData = new UserData();

        assertThatExceptionOfType(EntityNotFoundException.class).isThrownBy(() -> bookingService.getAllBookedSlotsByUserId(userData.getId()));
    }

    @Test
    public void testGetAllCanceledReservation() {

        Date createDate = new Date();
        Date timeSlotData = new Date();
        Date cancelTime = new Date();

        PlaceDetails placeDetails = new PlaceDetails();
        placeDetails.setPlaceId(1L);

        Long userId = 1L;
        UserData userData = new UserData();
        userData.setId(userId);

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setId(1L);
        timeSlot.setReservation(placeDetails);
        timeSlot.setTimeSlot(timeSlotData);
        timeSlot.setBookingCreationTime(createDate);
        timeSlot.setPersonalRequest("something");
        timeSlot.setBookingCancelTime(cancelTime);

        List<TimeSlot> timeSlotList = new ArrayList<>();
        timeSlotList.add(timeSlot);

        List<BookedTimeSlotData> expectedList = new ArrayList<>();
        BookedTimeSlotData bookedTimeSlotData = new BookedTimeSlotData(1L, 1L, timeSlotData, createDate, "something");
        expectedList.add(bookedTimeSlotData);

        when(userRepository.findById(userId)).thenReturn(Optional.of(new UserData()));
        when(timeSlotRepository.findTimeSlotByUserDataAndIsBookedAndBookingCancelTimeIsNotNull(userData, false)).thenReturn(timeSlotList);

        List<BookedTimeSlotData> resultList = bookingService.getAllCanceledReservation(userId);

        assertEquals(resultList, expectedList);
    }

    @Test
    public void testGetAllCanceledReservationFailBecauseOfNotPresentUser() {

        UserData userData = new UserData();

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> bookingService.getAllCanceledReservation(userData.getId()));
    }

    @Test
    public void testDeleteReservationByIdEntityNotFoundException(){

        UserData userData = new UserData();
        Long timeSlot = 1L;

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> bookingService.deleteReservationById(userData.getId(), timeSlot));
    }

    @Test
    public void testDeleteReservationById(){
        Date createDate = new Date();
        Date timeSlotData = new Date();
        Date cancelTime = new Date();

        PlaceDetails placeDetails = new PlaceDetails();
        placeDetails.setPlaceId(1L);

        Long userId = 1L;
        UserData userData = new UserData();
        userData.setId(userId);

        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setId(1L);
        timeSlot.setReservation(placeDetails);
        timeSlot.setTimeSlot(timeSlotData);
        timeSlot.setBookingCreationTime(createDate);
        timeSlot.setPersonalRequest("something");
        timeSlot.setBookingCancelTime(cancelTime);

        List<TimeSlot> timeSlotList = new ArrayList<>();
        timeSlotList.add(timeSlot);

        when(userRepository.findById(userId)).thenReturn(Optional.of(new UserData()));
        when(timeSlotRepository.findTimeSlotByUserDataAndIsBooked(userData, true)).thenReturn(timeSlotList);

        bookingService.deleteReservationById(userData.getId(), timeSlot.getId());

        verify(timeSlotRepository,atLeastOnce()).saveAndFlush(timeSlot);
    }

    @Test
    public void testAddBookingUserNotFoundException(){

        BookingDetails bookingDetails = new BookingDetails();

        assertThatExceptionOfType(UserNotFoundException.class)
                .isThrownBy(() -> bookingService.addBooking(bookingDetails));
    }
    @Test
    public void testAddBookingTimeSlotNotFoundException() {

        Long userId = 1L;
        UserData userData = new UserData();
        userData.setId(userId);

        BookingDetails bookingDetails = new BookingDetails();


        when(userRepository.findById(bookingDetails.getPlaceId())).thenReturn(Optional.of(new UserData()));
        when(placeDetailsRepository.findById(bookingDetails.getPlaceId())).thenReturn(Optional.of(new PlaceDetails()));

        assertThatExceptionOfType(TimeslotNotFoundException.class)
                .isThrownBy(() -> bookingService.addBooking(bookingDetails));
    }
}

