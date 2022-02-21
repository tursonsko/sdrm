package pjatk.sdrm.model.dto.timeslot;

import java.util.Date;

public class TimeSlotDtoSimplified {
    private Long id;
    private Date timeSlot;

    private boolean isBooked;

    public TimeSlotDtoSimplified(Long id, Date timeSlot, boolean isBooked) {
        this.id = id;
        this.timeSlot = timeSlot;
        this.isBooked = isBooked;
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
}
