package pl.coderslab.charity.admin;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.HomeController;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.CurrentUserId;
import pl.coderslab.charity.user.UserServiceImpl;

@Controller
public class AdminController {


    private final HomeController homeController;

    public AdminController(HomeController homeController) {
        this.homeController = homeController;
    }

    @GetMapping("/admin/**")
    public String homeAction(@AuthenticationPrincipal CurrentUserId currentUserId, Model model) {

        homeController.homeAction(currentUserId, model);
        return "admin/admin";
    }

}
