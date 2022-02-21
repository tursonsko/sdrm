package pjatk.sdrm.service;

import org.springframework.stereotype.Service;
import pjatk.sdrm.exception.UserNotFoundException;
import pjatk.sdrm.mapper.SpaceDetailMapper;
import pjatk.sdrm.model.dto.spacedetail.SpaceDetailDtoRequest;
import pjatk.sdrm.model.dto.spacedetail.SpaceDetailDtoResponse;
import pjatk.sdrm.model.entity.SpaceDetail;
import pjatk.sdrm.model.entity.UserData;
import pjatk.sdrm.repository.SpaceDetailRepository;
import pjatk.sdrm.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpaceDetailService {

    private final SpaceDetailRepository spaceDetailRepository;
    private final UserRepository userRepository;

    public SpaceDetailService(SpaceDetailRepository spaceDetailRepository, UserRepository userRepository) {
        this.spaceDetailRepository = spaceDetailRepository;
        this.userRepository = userRepository;
    }

    public List<SpaceDetailDtoResponse> getAllSpaces(){
        return spaceDetailRepository.findAll().stream()
                .map(SpaceDetailMapper::mapToSpaceDetailDtoResponse).collect(Collectors.toList());
    }

    public SpaceDetailDtoResponse saveSpace(SpaceDetailDtoRequest spaceDetailRequest) throws UserNotFoundException {
        Long userId = spaceDetailRequest.getUserId();
        UserData user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format("No user with provided id %s in system", userId)));
        SpaceDetail spaceDetail = SpaceDetailMapper.mapToSpaceDetail(spaceDetailRequest, user);
        spaceDetailRepository.save(spaceDetail);
        return SpaceDetailMapper.mapToSpaceDetailDtoResponse(spaceDetail);
    }
}
