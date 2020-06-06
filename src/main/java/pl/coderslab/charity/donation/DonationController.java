package pl.coderslab.charity.donation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.category.CategoryService;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.CurrentUserId;
import pl.coderslab.charity.user.UserServiceImpl;


@Controller
@RequestMapping("/form")
public class DonationController {

    private final CategoryService categoryService;
    private final DonationService donationService;
    private final InstitutionService institutionService;
    private final UserServiceImpl userServiceImpl;

    public DonationController(CategoryService categoryService, DonationService donationService, InstitutionService institutionService, UserServiceImpl userServiceImpl) {
        this.categoryService = categoryService;
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/form")
    public String formAction(@AuthenticationPrincipal CurrentUserId currentUserId, Model model) {
        model.addAttribute("user", userServiceImpl.findById(currentUserId.getUserId()));
        model.addAttribute("donation", new Donation());
        model.addAttribute("categories", categoryService.finaAll());
        model.addAttribute("institutions", institutionService.finaAll());
        return "form";
    }

    //TODO VALIDATION doesnt work
    @PostMapping("/form")
    public String formAction(@AuthenticationPrincipal CurrentUserId currentUserId, Donation donation, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:form";
        }
        donation.setUser(userServiceImpl.findById(currentUserId.getUserId()));
        donationService.save(donation);
        return "redirect:form-confirmation";
    }

    @GetMapping("/form-confirmation")
    public String formConfirm(@AuthenticationPrincipal CurrentUserId currentUserId, Model model) {
        model.addAttribute("user", userServiceImpl.findById(currentUserId.getUserId()));

        return "form-confirmation";
    }

}
