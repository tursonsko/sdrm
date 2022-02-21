package pjatk.sdrm.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "reservation")
public class PlaceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long placeId;

    private String reservationName;

    private Date reservationStartDate;

    private Date reservationEndDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "space_id")
    private SpaceDetail spaceDetail;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationCreateDate;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference(value = "placedetails_u")
    private UserData userData;

    @OneToMany( mappedBy= "reservation",
            cascade = CascadeType.ALL)
    @JsonManagedReference(value = "placedetails_t")
    private List<TimeSlot> timeSlots = new ArrayList<>();

    @OneToOne(mappedBy = "placeDetails")
    private SpaceCoordinates spaceCoordinates;


    public PlaceDetails(String reservationName, Date reservationStartDate, Date reservationEndDate, Date reservationCreateDate, UserData userData, List<TimeSlot> timeSlot,SpaceCoordinates spaceCoordinates ,SpaceDetail spaceDetail) {
        this.reservationName = reservationName;
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.reservationCreateDate = reservationCreateDate;
        this.userData = userData;
        this.timeSlots = timeSlot;
        this.spaceDetail = spaceDetail;
        this.spaceCoordinates = spaceCoordinates;
    }

    public PlaceDetails() {
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long reservationId) {
        this.placeId = reservationId;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public Date getReservationStartDate() {
        return reservationStartDate;
    }

    public void setReservationStartDate(Date reservationStartDate) {
        this.reservationStartDate = reservationStartDate;
    }

    public Date getReservationEndDate() {
        return reservationEndDate;
    }

    public void setReservationEndDate(Date reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }

    public SpaceDetail getSpaceDetail() {
        return spaceDetail;
    }

    public void setSpaceDetail(SpaceDetail spaceDetail) {
        this.spaceDetail = spaceDetail;
    }

    public Date getReservationCreateDate() {
        return reservationCreateDate;
    }

    public void setReservationCreateDate(Date reservationCreateDate) {
        this.reservationCreateDate = reservationCreateDate;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<TimeSlot> getTimeSlots() {
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }

    public SpaceCoordinates getSpaceCoordinates() {
        return spaceCoordinates;
    }

    public void setSpaceCoordinates(SpaceCoordinates spaceCoordinates) {
        this.spaceCoordinates = spaceCoordinates;
    }
}
