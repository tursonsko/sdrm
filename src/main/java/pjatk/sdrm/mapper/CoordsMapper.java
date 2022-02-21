package pjatk.sdrm.mapper;

import pjatk.sdrm.model.dto.SpaceCoordsDto;
import pjatk.sdrm.model.entity.PlaceDetails;
import pjatk.sdrm.model.entity.SpaceCoordinates;

public class CoordsMapper {
    public static SpaceCoordinates mapToSpaceCoordinates(SpaceCoordsDto spaceCoordsDto, PlaceDetails placeDetails){
        return new SpaceCoordinates(spaceCoordsDto.getTop(),spaceCoordsDto.getRight(),spaceCoordsDto.getBottom(),spaceCoordsDto.getLeft(),placeDetails);
    }

    public static SpaceCoordsDto mapToSpaceCoordsDto(SpaceCoordinates spaceCoordinates){
        return new SpaceCoordsDto(spaceCoordinates.getTop_c(),spaceCoordinates.getRight_c(),spaceCoordinates.getBottom_c(),spaceCoordinates.getLeft_c());
    }
}
