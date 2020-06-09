package pl.coderslab.charity.registration;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailController {

    private MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/sendemail")
    public String sendEmail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("stanislaw.jakubowski@o2.pl");
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("charityApp@gmail.com");
        mailMessage.setText("To confirm your account, please be good");
        mailService.sendEmail(mailMessage);

        return "index";
    }
}