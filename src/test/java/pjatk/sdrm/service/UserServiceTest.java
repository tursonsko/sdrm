package pjatk.sdrm.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import pjatk.sdrm.exception.DuplicatedUsernameException;
import pjatk.sdrm.exception.ReservationNotFoundException;
import pjatk.sdrm.model.dto.SignUpDto;
import pjatk.sdrm.model.dto.UserDtoRequest;
import pjatk.sdrm.model.dto.UserDtoResponse;
import pjatk.sdrm.model.entity.UserData;
import pjatk.sdrm.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        UserDtoResponse userDtoResponse = new UserDtoResponse("nameTest", "lastNameTest", "test@email");

        List<UserDtoResponse> users = new ArrayList<>();
        users.add(userDtoResponse);

        UserData userData = new UserData();
        userData.setUserEmail("test@email");
        List<UserData> list = new ArrayList<>();
        list.add(userData);

        when(userRepository.findAll()).thenReturn(list);
        List<UserDtoResponse> resultList = userService.findAllUsers();

        assertEquals(resultList, users);
    }

    @Test
    public void testFindById() {
        Long userId = 1L;
        UserData userData = new UserData();
        userData.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(userData));

        assertNotNull(userService.findById(userId));
    }

    @Test
    public void testSaveUserFailBecauseOfDuplicatedUserName() {

        UserDtoRequest userDtoRequest = new UserDtoRequest("testName", "testLastName", "test@email");

        UserData userData = new UserData();
        userData.setUserName("testName");
        List<UserData> resultList = new ArrayList<>();
        resultList.add(userData);

        when(userRepository.findAll()).thenReturn(resultList);

        assertThatExceptionOfType(DuplicatedUsernameException.class).isThrownBy(() -> userService.save(userDtoRequest));
    }

    @Test
    public void testSaveUserThrowNullPointer() {

        UserDtoRequest userDtoRequest = new UserDtoRequest("testName", "testLastName", "test@email");
        userDtoRequest.setPassword("password");

        Long userId = 1L;
        UserData userData = new UserData();
        userData.setUserName("testName2");
        userData.setId(userId);

        List<UserData> resultList = new ArrayList<>();
        resultList.add(userData);

        when(userRepository.findAll()).thenReturn(resultList);
        when(passwordEncoder.encode(userDtoRequest.getPassword())).thenReturn("encodedPassword");

        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> userService.save(userDtoRequest));
    }


//    @Test
//    public void testUserNotFoundException() {
//        UserData userData = new UserData();
//        userData.setId(1L);
//        userData.setUserEmail("test@email");
//
//
//        assertThatExceptionOfType(UserNotFoundException.class)
//                .isThrownBy(() -> userService.loadUserByUsername(userData.getUserEmail()));
//    }

    @Test
    public void testLoadUserDataByUserName() {

        UserData userData = new UserData();
        userData.setUserName("testName");

        userService.loadUserDataByUserName(userData.getUserName());

        verify(userRepository, atLeastOnce()).findUserDataByUserName(userData.getUserName());
    }


    @Test
    public void testFindAllReservationByUserId() throws ReservationNotFoundException {
        UserData userData = new UserData();
        userData.setId(1L);

        when(userRepository.findById(userData.getId())).thenReturn(Optional.of(new UserData()));

        Optional<UserData> userD = userRepository.findById(userData.getId());
        userService.findAllReservationsByUserId(userData.getId());

        assertNotNull(userD);
    }

    @Test
    public void testFindAllReservationByUserIdThrowReservationFoundException() {
        UserData userData = new UserData();
        userData.setId(1L);

        assertThatExceptionOfType(ReservationNotFoundException.class).isThrownBy(() -> userService.findAllReservationsByUserId(userData.getId()));
    }

    @Test
    public void testSignUpUserRuntimeException() {
        SignUpDto signUpDto = new SignUpDto();

        assertThatExceptionOfType(RuntimeException.class).isThrownBy(() -> userService.signUpUser(signUpDto));
    }
}