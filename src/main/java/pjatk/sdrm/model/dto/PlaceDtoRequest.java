package pjatk.sdrm.model.dto;

import io.micrometer.core.lang.NonNull;
import java.util.Date;

public class PlaceDtoRequest {

    @NonNull
    private String reservationName;

    @NonNull
    private Date reservationStartDate;

    @NonNull
    private Date reservationEndDate;

    @NonNull
    private Long reservationSpaceId;

    @NonNull
    private Date reservationCreateDate;

    @NonNull
    private SpaceCoordsDto coords;

    @NonNull
    private Long userData;

    public PlaceDtoRequest(@NonNull String reservationName, @NonNull Date reservationStartDate, @NonNull Date reservationEndDate,
                           @NonNull Long reservationSpaceId, @NonNull Date reservationCreateDate, SpaceCoordsDto coords,
                           @NonNull Long userData) {
        this.reservationName = reservationName;
        this.reservationStartDate = reservationStartDate;
        this.reservationEndDate = reservationEndDate;
        this.reservationSpaceId = reservationSpaceId;
        this.reservationCreateDate = reservationCreateDate;
        this.coords = coords;
        this.userData = userData;
    }

    @NonNull
    public String getReservationName() {
        return reservationName;
    }

    @NonNull
    public Date getReservationStartDate() {
        return reservationStartDate;
    }

    @NonNull
    public Date getReservationEndDate() {
        return reservationEndDate;
    }

    @NonNull
    public Long getReservationSpaceId() {
        return reservationSpaceId;
    }

    @NonNull
    public Date getReservationCreateDate() {
        return reservationCreateDate;
    }

    @NonNull
    public SpaceCoordsDto getCoords() {
        return coords;
    }

    @NonNull
    public Long getUserData() {
        return userData;
    }
}
