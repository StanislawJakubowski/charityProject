package pl.coderslab.charity.category;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.donation.DonationService;
import pl.coderslab.charity.institution.Institution;
import pl.coderslab.charity.institution.InstitutionService;
import pl.coderslab.charity.user.CurrentUserId;
import pl.coderslab.charity.user.UserServiceImpl;

import javax.validation.Valid;

@RequestMapping("/admin/category")
@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final DonationService donationService;
    private final InstitutionService institutionService;
    private final UserServiceImpl userServiceImpl;


    public CategoryController(CategoryService categoryService, DonationService donationService, InstitutionService institutionService, UserServiceImpl userServiceImpl) {
        this.categoryService = categoryService;
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/list")
    public String getCategories(@AuthenticationPrincipal CurrentUserId currentUserId, Model model) {
        if (currentUserId != null) {
            model.addAttribute("user", userServiceImpl.findById(currentUserId.getUserId()));
        }
        model.addAttribute("categories", categoryService.finaAll());
        return "admin/categoryList";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable long id) {
        categoryService.deleteCategoryRelations(id);
        categoryService.delete(id);
        return "redirect:../list";
    }

    @GetMapping("/add")
    public String addCategory(Model model) {
        if (!model.containsAttribute("category")) model.addAttribute("category", new Category());
        model.addAttribute("category", new Category());
        return "admin/category";
    }

    @PostMapping("/add")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.messageForm", result);
            redirectAttributes.addFlashAttribute("category", category);
            return "admin/category";
        }
        categoryService.save(category);
        return "redirect:list";
    }

    @GetMapping("/edit/{id}")
    public String editCategory(Model model, @PathVariable long id) {

        Category category = categoryService.findById(id);
        model.addAttribute("category", category);
        return "admin/category";
    }

    @PostMapping("/edit/{id}")
    public String editCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.messageForm", result);
            redirectAttributes.addFlashAttribute("category", category);
            return "admin/category";
        }
        categoryService.update(category);
        return "redirect:../list";
    }

}
