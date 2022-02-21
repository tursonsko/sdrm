package pjatk.sdrm.mapper;

import pjatk.sdrm.model.dto.PlaceDtoRequest;
import pjatk.sdrm.model.dto.PlaceDtoResponse;
import pjatk.sdrm.model.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationMapper {

    public static PlaceDetails mapToReservationDtoEntity(PlaceDtoRequest placeDtoRequest, UserData userData, List<TimeSlot> timeSlotList, SpaceDetail spaceDetail) {
        return new PlaceDetails(
                placeDtoRequest.getReservationName(),
                placeDtoRequest.getReservationStartDate(),
                placeDtoRequest.getReservationEndDate(),
                placeDtoRequest.getReservationCreateDate(),
                userData,
                timeSlotList,
                null,
                spaceDetail);
    }

    public static PlaceDtoResponse mapToReservationDtoResponse(PlaceDetails placeDetails) {
        return new PlaceDtoResponse(
                placeDetails.getPlaceId(),
                placeDetails.getReservationName(),
                placeDetails.getReservationStartDate(),
                placeDetails.getReservationEndDate(),
                placeDetails.getSpaceDetail().getId(),
                placeDetails.getReservationCreateDate(),
                UserMapper.mapToUserDtoResponse(placeDetails.getUserData()),
                CoordsMapper.mapToSpaceCoordsDto(placeDetails.getSpaceCoordinates()),
                placeDetails.getTimeSlots().stream().map(TimeSlotMapper::mapToTimeSlotDtoSimplified).collect(Collectors.toList())
        );
    }
}
