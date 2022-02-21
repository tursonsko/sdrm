package pjatk.sdrm.mapper;

import pjatk.sdrm.model.dto.timeslot.BookedTimeSlotData;
import pjatk.sdrm.model.dto.timeslot.TimeSlotDtoDetailed;
import pjatk.sdrm.model.dto.timeslot.TimeSlotDtoSimplified;
import pjatk.sdrm.model.entity.TimeSlot;

import java.util.Objects;

public class TimeSlotMapper {

    public static TimeSlotDtoDetailed mapToTimeSlotDtoDetailed(TimeSlot timeSlot) {
        return new TimeSlotDtoDetailed(timeSlot.getId(), timeSlot.getTimeSlot(), timeSlot.isBooked(), timeSlot.getBookingCreationTime(),
                timeSlot.getBookingCancelTime(),timeSlot.getPersonalRequest(), Objects.isNull(timeSlot.getUserData())? null : UserMapper.mapToUserDtoResponse(timeSlot.getUserData()));
    }

    public static TimeSlotDtoSimplified mapToTimeSlotDtoSimplified(TimeSlot timeSlot) {
        return new TimeSlotDtoSimplified(timeSlot.getId(), timeSlot.getTimeSlot(), timeSlot.isBooked());
    }
    
    public static BookedTimeSlotData mapToBookedTimeSlotData(TimeSlot timeSlot){
        return new BookedTimeSlotData(timeSlot.getId(),
                timeSlot.getReservation().getPlaceId(),
                timeSlot.getTimeSlot(),
                timeSlot.getBookingCreationTime(),
                timeSlot.getPersonalRequest());
    }
}
