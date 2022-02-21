package pjatk.sdrm.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pjatk.sdrm.service.MailerService;

@RestController
@RequestMapping("/api/email")
public class MailerController {

    private MailerService mailer;

    public MailerController(MailerService mailer) {
        this.mailer = mailer;
    }

    @PostMapping("/reservation/{idUser}")
    public String sendMail(@PathVariable Long idUser) {
        mailer.sendMail(idUser);
        return "mail sent";
    }
}
