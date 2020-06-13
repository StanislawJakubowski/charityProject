package pl.coderslab.charity.registration;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pl.coderslab.charity.registration.token.ConfirmationToken;

@Service
public class MailService {

    private JavaMailSender javaMailSender;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {

        javaMailSender.send(email);
    }

    @Async
    public void sendRegistrationEmail(String email, ConfirmationToken confirmationToken){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("charityApp@gmail.com");
        mailMessage.setText("To confirm your account, please click here : " +
                "http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
        javaMailSender.send(mailMessage);
    }
}
