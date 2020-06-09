package pl.coderslab.charity.registration;

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
        mailService.sendSimpleEmail("stanislaw.jakubowski@o2.pl", "Test e-mail by ME", "Testing email functionality");

        return "index";
    }
}