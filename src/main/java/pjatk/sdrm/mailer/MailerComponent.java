package pjatk.sdrm.mailer;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailerComponent {

    private JavaMailSender emailSender;
    private MailTextFactory textFactory;

    public MailerComponent(JavaMailSender emailSender, MailTextFactory textFactory) {
        this.emailSender = emailSender;
        this.textFactory = textFactory;
    }

    public void sendMessage(String email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject(textFactory.getSubject());
            message.setText(textFactory.getText());
            emailSender.send(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void sendConfirmationMail(String userEmail, String template) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(userEmail);
            message.setSubject(textFactory.getSubject());
            message.setText(template);
            emailSender.send(message);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}