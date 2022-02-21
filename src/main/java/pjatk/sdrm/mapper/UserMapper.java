package pjatk.sdrm.mapper;

import pjatk.sdrm.model.dto.UserDtoRequest;
import pjatk.sdrm.model.dto.UserDtoResponse;
import pjatk.sdrm.model.entity.UserData;

public class UserMapper {

    public static UserData mapToUserDataEntity(UserDtoRequest userDtoRequest, String encodePwd){
        return new UserData(userDtoRequest.getName(), userDtoRequest.getLastName()
                ,encodePwd, userDtoRequest.getEmail());
    }

    public static UserDtoResponse mapToUserDtoResponse(UserData userData){
        return new UserDtoResponse(userData.getUserName(),userData.getLastName(),userData.getUserEmail());
    }
}
