package pjatk.sdrm.model.dto;

import java.util.Date;

public class BookingDetails {

    private Long placeId;
    private Long timeSlotId;
    private Long userId;
    private Date bookingCreationTime;
    private Date bookingCancelTime;
    private boolean bookPlace;
    private String additionalInfo;

    public BookingDetails(Long placeId, Long timeSlotId, Long userId,
                          Date bookingCreationTime, Date bookingCancelTime,
                          boolean status, String personalRequest) {
        this.placeId = placeId;
        this.timeSlotId = timeSlotId;
        this.userId = userId;
        this.bookingCreationTime = bookingCreationTime;
        this.bookingCancelTime = bookingCancelTime;
        this.bookPlace = status;
        this.additionalInfo = personalRequest;
    }

    public BookingDetails() {
    }

    public Long getPlaceId() {
        return placeId;
    }

    public Long getTimeSlotId() {
        return timeSlotId;
    }

    public Long getUserId() {
        return userId;
    }

    public Date getBookingCreationTime() {
        return bookingCreationTime;
    }

    public Date getBookingCancelTime() {
        return bookingCancelTime;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public boolean getBookPlace() {
        return bookPlace;
    }
}
