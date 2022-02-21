package pjatk.sdrm.mapper;

import pjatk.sdrm.model.dto.spacedetail.SpaceDetailDtoRequest;
import pjatk.sdrm.model.dto.spacedetail.SpaceDetailDtoResponse;
import pjatk.sdrm.model.entity.SpaceDetail;
import pjatk.sdrm.model.entity.UserData;

public class SpaceDetailMapper {

    public static SpaceDetail mapToSpaceDetail(SpaceDetailDtoRequest spaceDetailDtoRequest, UserData user) {
        return new SpaceDetail(spaceDetailDtoRequest.getName(), spaceDetailDtoRequest.getImageBase64(), user);
    }

    public static SpaceDetailDtoResponse mapToSpaceDetailDtoResponse(SpaceDetail spaceDetail) {
        return new SpaceDetailDtoResponse(spaceDetail.getId(), spaceDetail.getName(), spaceDetail.getImageBase64());
    }
}
