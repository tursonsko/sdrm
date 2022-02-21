package pjatk.sdrm.service;

import org.springframework.stereotype.Service;
import pjatk.sdrm.mailer.MailerComponent;
import pjatk.sdrm.model.entity.UserData;
import pjatk.sdrm.repository.UserRepository;

import java.util.Optional;

@Service
public class MailerService {

    private MailerComponent mailerComponent;
    private UserRepository userRepository;

    public MailerService(MailerComponent mailerComponent, UserRepository userRepository) {
        this.mailerComponent = mailerComponent;
        this.userRepository = userRepository;
    }

    public void sendMail(Long userId) {
        Optional<UserData> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            UserData userData = optionalUser.get();
            mailerComponent.sendMessage(userData.getUserEmail());
        }
    }
}
