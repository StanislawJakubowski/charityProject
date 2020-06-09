package pl.coderslab.charity.registration;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.coderslab.charity.user.User;
import pl.coderslab.charity.user.UserServiceImpl;

@Controller
public class ConfirmAccountController {

    private final UserServiceImpl userService;
    private final ConfirmationTokenService confirmationTokenService;

    public ConfirmAccountController(UserServiceImpl userService, ConfirmationTokenService confirmationTokenService) {
        this.userService = userService;
        this.confirmationTokenService = confirmationTokenService;;
    }

    @GetMapping("/confirm-account")
    public String confirmUserAccount(Model model, @RequestParam("token")String confirmationToken){
        ConfirmationToken token = confirmationTokenService.findByConfirmationToken(confirmationToken);

        if(token != null){
            User user = token.getUser();
            user.setEnabled(1);
            userService.updateUserAfterEmailConfirmation(user);
        }
        else{
            return "redirect:index";
        }
        return "loginAndRegistry/confirm-account";
    }



}
