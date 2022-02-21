package pjatk.sdrm.service;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import pjatk.sdrm.exception.PlaceNotFoundException;
import pjatk.sdrm.exception.SpaceNotFoundException;
import pjatk.sdrm.exception.WrongReservationDateException;
import pjatk.sdrm.model.dto.PlaceDtoRequest;
import pjatk.sdrm.model.entity.PlaceDetails;
import pjatk.sdrm.model.entity.SpaceDetail;
import pjatk.sdrm.model.entity.UserData;
import pjatk.sdrm.repository.PlaceDetailsRepository;
import pjatk.sdrm.repository.SpaceCoordinatesRepository;
import pjatk.sdrm.repository.SpaceDetailRepository;
import pjatk.sdrm.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlaceDetailsServiceTest {

    @Mock
    private PlaceDetailsRepository placeDetailsRepository;

    @Mock
    private SpaceDetailRepository spaceDetailRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SpaceCoordinatesRepository spaceCoordinatesRepository;

    @InjectMocks
    private PlaceDetailsService placeDetailsService;

    @Test
    public void testDeleteById(){

        PlaceDetails placeDetails = new PlaceDetails();
        placeDetails.setPlaceId(1L);

        placeDetailsService.deleteById(placeDetails.getPlaceId());

        verify(placeDetailsRepository,atLeastOnce()).deleteById(1L);
    }

    @Test
    public void testSaveSpaceNotFound()  {
        Date resDate = new Date();
        Date resEndDate = new Date();
        UserData userData = new UserData();
        userData.setId(1L);

        PlaceDtoRequest placeDtoRequest =new PlaceDtoRequest(null,resDate,resEndDate,1L,null,null,userData.getId());

        assertThatExceptionOfType(SpaceNotFoundException.class)
                .isThrownBy(() -> placeDetailsService.save(placeDtoRequest));
    }

    @Test
    public void testSaveWrongReservationDateException()  {
        Date resStart = new GregorianCalendar(2022, Calendar.JUNE, 25, 5, 0)
                .getTime();
        Date wrongEndDate = new GregorianCalendar(2022, Calendar.JUNE, 24, 5, 0)
                .getTime();
        UserData userData = new UserData();
        userData.setId(1L);

        PlaceDtoRequest placeDtoRequest =new PlaceDtoRequest(null,resStart,wrongEndDate,1L,null,null,userData.getId());

        when(spaceDetailRepository.findById(placeDtoRequest.getReservationSpaceId())).thenReturn(Optional.of(new SpaceDetail()));

        assertThatExceptionOfType(WrongReservationDateException.class)
                .isThrownBy(() -> placeDetailsService.save(placeDtoRequest));
    }

    @Test
    public void testSaveEntityNotFoundException()  {
        Date resDate = new Date();
        Date resEndDate = new Date();
        UserData userData = new UserData();
        userData.setId(1L);

        PlaceDtoRequest placeDtoRequest =new PlaceDtoRequest(null,resDate,resEndDate,1L,null,null,userData.getId());

        when(spaceDetailRepository.findById(placeDtoRequest.getReservationSpaceId())).thenReturn(Optional.of(new SpaceDetail()));

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> placeDetailsService.save(placeDtoRequest));
    }

    @Test
    public void testFindByIdFail(){
        PlaceDetails placeDetails = new PlaceDetails();

        assertThatExceptionOfType(PlaceNotFoundException.class)
                .isThrownBy(() -> placeDetailsService.findById(placeDetails.getPlaceId()));
    }

    @Test
    public void testFindById(){
        PlaceDetails placeDetails = new PlaceDetails();

        when(placeDetailsRepository.findById(placeDetails.getPlaceId())).thenReturn(Optional.of(new PlaceDetails()));
        Optional<PlaceDetails> result = placeDetailsRepository.findById(placeDetails.getPlaceId());
        assertNotNull(result);
    }
}
