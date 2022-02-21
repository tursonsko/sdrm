package pjatk.sdrm.helpers;

import pjatk.sdrm.model.entity.PlaceDetails;
import pjatk.sdrm.model.entity.UserData;

import java.util.Date;

public class EmailMinimalRequestHelper {

    private Long userId;
    private Date reservationStartDate;
    private Date reservationEndDate;

    public EmailMinimalRequestHelper(UserData userData, PlaceDetails placeDetails) {
        this.userId = userData.getId();
        this.reservationStartDate = placeDetails.getReservationStartDate();
        this.reservationEndDate = placeDetails.getReservationEndDate();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
}
