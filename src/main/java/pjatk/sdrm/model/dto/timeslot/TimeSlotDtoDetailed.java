package pjatk.sdrm.model.dto.timeslot;


import pjatk.sdrm.model.dto.UserDtoResponse;

import java.util.Date;

public class TimeSlotDtoDetailed {
    private Long id;
    private Date timeSlot;

    private boolean isBooked;

    private Date bookingCreationTime;

    private Date bookingCancelTime;

    private String personalRequest;

    private UserDtoResponse userDtoData;

    public TimeSlotDtoDetailed(Date timeSlot, boolean isBooked) {
        this.timeSlot = timeSlot;
        this.isBooked = isBooked;
    }

    public TimeSlotDtoDetailed(Long id, Date timeSlot, boolean isBooked,
                               Date bookingCreationTime,
                               Date bookingCancelTime,
                               String personalRequest,
                               UserDtoResponse userDtoData) {
        this.id = id;
        this.timeSlot = timeSlot;
        this.isBooked = isBooked;
        this.bookingCreationTime = bookingCreationTime;
        this.bookingCancelTime = bookingCancelTime;
        this.personalRequest = personalRequest;
        this.userDtoData = userDtoData;
    }

    public Long getId() {
        return id;
    }

    public Date getTimeSlot() {
        return timeSlot;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public Date getBookingCreationTime() {
        return bookingCreationTime;
    }

    public Date getBookingCancelTime() {
        return bookingCancelTime;
    }

    public String getPersonalRequest() {
        return personalRequest;
    }

    public UserDtoResponse getUserDtoData() {
        return userDtoData;
    }
}
