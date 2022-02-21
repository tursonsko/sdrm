package pjatk.sdrm.model.dto;

import java.util.Date;
import java.util.List;

public class PlaceDtoResponse<T> {

    private Long reservationId;

    private String reservationName;

    private Date reservationStartDate;

    private Date reservationEndDate;

    private Long reservationSpaceId;

    private Date reservationCreateDate;

    private UserDtoResponse userData;

    private SpaceCoordsDto coords;

    private List<T> timeSlots;

    public PlaceDtoResponse(Long reservationId, String reservationName, Date reservationStartDate, Date reservationEndDate, Long reservationSpaceId, Date reservationCreateDate, UserDtoResponse userData,SpaceCoordsDto coords, List<T> timeSlots) {
        this.reservationId = reservationId;
        this.reservationName = reservationName;
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.reservationSpaceId = reservationSpaceId;
        this.reservationCreateDate = reservationCreateDate;
        this.userData = userData;
        this.timeSlots = timeSlots;
        this.coords = coords;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public String getReservationName() {
        return reservationName;
    }

    public void setReservationName(String reservationName) {
        this.reservationName = reservationName;
    }

    public Date getReservationCreateDate() {
        return reservationCreateDate;
    }

    public void setReservationCreateDate(Date reservationCreateDate) {
        this.reservationCreateDate = reservationCreateDate;
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

    public Long getReservationSpaceId() {
        return reservationSpaceId;
    }

    public void setReservationSpaceId(Long reservationSpaceId) {
        this.reservationSpaceId = reservationSpaceId;
    }

    public UserDtoResponse getUserData() {
        return userData;
    }

    public void setUserData(UserDtoResponse userData) {
        this.userData = userData;
    }

    public SpaceCoordsDto getCoords() {
        return coords;
    }

    public List<T> getTimeSlots() {
        return timeSlots;
    }
}
