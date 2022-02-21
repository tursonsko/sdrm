package pjatk.sdrm.model.dto.timeslot;

import java.util.Date;

public class BookedTimeSlotData {

    private Long bookedPlaceId;
    private Long reservationId;
    private Date timeSlot;
    private Date bookingCreationTime;
    private String personalRequest;

    public BookedTimeSlotData(Long spaceId,Long reservationId,Date timeSlot, Date bookingCreationTime,
                            String personalRequest) {
        this.bookedPlaceId = spaceId;
        this.reservationId = reservationId;
        this.timeSlot = timeSlot;
        this.bookingCreationTime = bookingCreationTime;
        this.personalRequest = personalRequest;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public Long getBookedPlaceId() {
        return bookedPlaceId;
    }
    public Date getTimeSlot() {
        return timeSlot;
    }
    
    public Date getBookingCreationTime() {
        return bookingCreationTime;
    }

    public String getPersonalRequest() {
        return personalRequest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookedTimeSlotData that = (BookedTimeSlotData) o;

        if (bookedPlaceId != null ? !bookedPlaceId.equals(that.bookedPlaceId) : that.bookedPlaceId != null)
            return false;
        if (reservationId != null ? !reservationId.equals(that.reservationId) : that.reservationId != null)
            return false;
        if (timeSlot != null ? !timeSlot.equals(that.timeSlot) : that.timeSlot != null) return false;
        if (bookingCreationTime != null ? !bookingCreationTime.equals(that.bookingCreationTime) : that.bookingCreationTime != null)
            return false;
        return personalRequest != null ? personalRequest.equals(that.personalRequest) : that.personalRequest == null;
    }
}
