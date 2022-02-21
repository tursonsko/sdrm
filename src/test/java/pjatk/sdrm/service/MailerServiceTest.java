package pjatk.sdrm.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pjatk.sdrm.mailer.MailerComponent;
import pjatk.sdrm.model.entity.UserData;
import pjatk.sdrm.repository.UserRepository;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MailerServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MailerComponent mailerComponent;

    @InjectMocks
    private MailerService mailerService;

    @Test
    public void testSendEmail(){
        UserData userData = new UserData();
        userData.setId(1L);

        Optional<UserData> optUserData = userRepository.findById(userData.getId());

        when(userRepository.findById(userData.getId())).thenReturn(Optional.of(new UserData()));

        mailerService.sendMail(userData.getId());

        verify(mailerComponent,atLeastOnce()).sendMessage(userData.getUserEmail());
        assertNotNull(optUserData);
    }
}
