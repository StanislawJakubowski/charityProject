package pl.coderslab.charity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.CurrentUserId;
import pl.coderslab.charity.user.UserServiceImpl;

@Controller
public class HomeController {

    private final CategoryService categoryService;
    private final DonationService donationService;
    private final InstitutionService institutionService;
    private final UserServiceImpl userServiceImpl;

    public HomeController(CategoryService categoryService, DonationService donationService, InstitutionService institutionService, UserServiceImpl userServiceImpl) {
        this.categoryService = categoryService;
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/")
    public String homeAction(@AuthenticationPrincipal CurrentUserId currentUserId, Model model) {
        homeActionMethod(currentUserId, model);
        return "index";
    }

    private void homeActionMethod(@AuthenticationPrincipal CurrentUserId currentUserId, Model model) {
        if (currentUserId != null) {
            model.addAttribute("user", userServiceImpl.findById(currentUserId.getUserId()));
        }
        model.addAttribute("institutions", institutionService.finaAll());
        Long sumOfQuantities = donationService.sumOfQuantities();
        Long sumOfDonations = donationService.sumOfDonations();
        model.addAttribute("sumOfQuantities", sumOfQuantities);
        model.addAttribute("sumOfDonations", sumOfDonations);
    }


}
