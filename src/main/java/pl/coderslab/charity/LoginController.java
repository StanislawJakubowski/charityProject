package pl.coderslab.charity;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.registration.ConfirmationToken;
import pl.coderslab.charity.registration.ConfirmationTokenService;
import pl.coderslab.charity.registration.MailService;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class LoginController {

    private final UserServiceImpl userService;
    private final ConfirmationTokenService confirmationTokenService;
    private final MailService mailService;

    public LoginController(UserServiceImpl userService, ConfirmationTokenService confirmationTokenService, MailService mailService) {
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;
        this.mailService = mailService;
    }

    // deleted login model
    @GetMapping("/login")
    public String loginAction() {
        return "loginAndRegistry/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerAction(Model model) {
        model.addAttribute("user", new User());
        return "loginAndRegistry/register";
    }

    //TODO set sending email to confirm registration
    @PostMapping("/register")
    public String registerAction(@Valid @ModelAttribute("user") User user, BindingResult result,
                                 @RequestParam String password2, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.messageForm", result);
            redirectAttributes.addFlashAttribute("user", user);
            return "loginAndRegistry/register";
        }
        User existingUser = userService.findByEmail(user.getEmail());
        if (existingUser != null) {
            result.addError(new FieldError("user", "email",
                    "Email już istnieje"));
            return "loginAndRegistry/register";

        } else if (!password2.equals(user.getPassword())) {
            result.addError(new FieldError("user", "password",
                    "Hasła nie są takie same"));
            return "loginAndRegistry/register";
        }

        user.setPassword(user.getPassword());
        userService.saveUser(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenService.save(confirmationToken);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("charityApp@gmail.com");
        mailMessage.setText("To confirm your account, please click here : " +
                "http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
        mailService.sendEmail(mailMessage);

        return "loginAndRegistry/login";
    }

}
