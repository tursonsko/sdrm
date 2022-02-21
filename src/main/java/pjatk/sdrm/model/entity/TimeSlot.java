package pjatk.sdrm.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import pjatk.sdrm.model.dto.BookingDetails;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "timeslot")
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // sprawdzic generowanie od 1
    private Long id;

    private Date timeSlot;

    private boolean isBooked;

    private Date bookingCreationTime;

    private Date bookingCancelTime;

    private String personalRequest;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference(value = "timeslot")
    private UserData userData;

    @ManyToOne
    @JoinColumn(name="place_id")
    @JsonBackReference(value = "placedetails_t")
    private PlaceDetails reservation;

    public TimeSlot() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimeSlot(Date timeSlot, boolean isBooked, PlaceDetails reservationId) {
        this.timeSlot = timeSlot;
        this.reservation = reservationId;
        this.isBooked = isBooked;
    }


    public Date getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(Date timeSlot) {
        this.timeSlot = timeSlot;
    }


    public PlaceDetails getReservation() {
        return reservation;
    }

    public void setReservation(PlaceDetails reservationId) {
        this.reservation = reservationId;
    }

    public Long getId() {
        return id;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public Date getBookingCreationTime() {
        return bookingCreationTime;
    }

    public void setBookingCreationTime(Date bookingCreationTime) {
        this.bookingCreationTime = bookingCreationTime;
    }

    public Date getBookingCancelTime() {
        return bookingCancelTime;
    }

    public void setBookingCancelTime(Date bookingCancelTime) {
        this.bookingCancelTime = bookingCancelTime;
    }

    public String getPersonalRequest() {
        return personalRequest;
    }

    public void setPersonalRequest(String personalRequest) {
        this.personalRequest = personalRequest;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public static TimeSlot update(TimeSlot origin, BookingDetails update, UserData userData){
        origin.setBooked(update.getBookPlace());
        origin.setBookingCancelTime(update.getBookingCancelTime());
        origin.setBookingCreationTime(update.getBookingCreationTime());
        origin.setPersonalRequest(update.getAdditionalInfo());
        origin.setUserData(userData);
        return origin;
    }
}
