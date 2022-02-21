package pjatk.sdrm.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pjatk.sdrm.exception.UserNotFoundException;
import pjatk.sdrm.model.dto.spacedetail.SpaceDetailDtoRequest;
import pjatk.sdrm.model.dto.spacedetail.SpaceDetailDtoResponse;
import pjatk.sdrm.model.entity.SpaceDetail;
import pjatk.sdrm.model.entity.UserData;
import pjatk.sdrm.repository.SpaceDetailRepository;
import pjatk.sdrm.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SpaceDetailsServiceTest {

    @Mock
    private SpaceDetailRepository spaceDetailRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SpaceDetailService spaceDetailService;

    @Test
    public void testGetAllSpaces(){

        SpaceDetailDtoResponse spaceDetailDtoResponse = new SpaceDetailDtoResponse(1L, "space", "image.jpg");

        List<SpaceDetailDtoResponse> spaces = new ArrayList<>();
        spaces.add(spaceDetailDtoResponse);

        SpaceDetail spaceDetail = new SpaceDetail();
        List<SpaceDetail> list = new ArrayList<>();
        list.add(spaceDetail);

        when(spaceDetailRepository.findAll()).thenReturn(list);
        List<SpaceDetailDtoResponse> resultList = spaceDetailService.getAllSpaces();

        assertEquals(resultList,spaces);
    }

    @Test
    public void testSaveSpaceUserNotFoundException() {

        SpaceDetailDtoRequest spaceDetailDtoRequest = new SpaceDetailDtoRequest("name","image.jpg",999L);

        assertThatExceptionOfType(UserNotFoundException.class).isThrownBy(() -> spaceDetailService.saveSpace(spaceDetailDtoRequest));
    }

    @Test
    public void testSaveSpace() throws UserNotFoundException {

        SpaceDetailDtoRequest spaceDetailDtoRequest = new SpaceDetailDtoRequest("name","image.jpg",1L);
        Long userId = spaceDetailDtoRequest.getUserId();
        UserData userData = new UserData();
        userData.setId(1L);

        SpaceDetail spaceDetail = new SpaceDetail();

        when(userRepository.findById(userId)).thenReturn(Optional.of(new UserData()));

        spaceDetailService.saveSpace(spaceDetailDtoRequest);

        verify(spaceDetailRepository,atLeastOnce()).save(spaceDetail);
    }
}