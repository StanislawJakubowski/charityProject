package pl.coderslab.charity.institution;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.coderslab.charity.user.CurrentUserId;
import pl.coderslab.charity.user.UserServiceImpl;
import javax.validation.Valid;

@RequestMapping("/admin/institution")
@Controller
public class InstitutionController {

    private final InstitutionService institutionService;
    private final UserServiceImpl userServiceImpl;


    public InstitutionController(InstitutionService institutionService, UserServiceImpl userServiceImpl) {
        this.institutionService = institutionService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/list")
    public String getInstitutions(@AuthenticationPrincipal CurrentUserId currentUserId, Model model) {
        if (currentUserId != null) {
            model.addAttribute("user", userServiceImpl.findById(currentUserId.getUserId()));
        }
        model.addAttribute("institutions", institutionService.finaAll());
        return "admin/institutionList";
    }

    @GetMapping("/delete/{id}")
    public String deleteInstitutionById(@PathVariable long id) {
        institutionService.delete(id);
        return "redirect:../list";
    }

    @GetMapping("/add")
    public String addInstitution(Model model) {
        if (!model.containsAttribute("institution")) model.addAttribute("institution", new Institution());
        model.addAttribute("institution", new Institution());
        return "admin/institution";
    }

    @PostMapping("/add")
    public String addInstitution(@Valid @ModelAttribute("institution") Institution institution, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.messageForm", result);
            redirectAttributes.addFlashAttribute("institution", institution);
            return "admin/institution";
        }
        institutionService.save(institution);
        return "redirect:list";
    }

    @GetMapping("/edit/{id}")
    public String editInstitution(Model model, @PathVariable long id) {

        Institution institution = institutionService.findById(id);
        model.addAttribute("institution", institution);
        return "admin/institution";
    }

    @PostMapping("/edit/{id}")
    public String editInstitution(@Valid @ModelAttribute("institution") Institution institution, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.messageForm", result);
            redirectAttributes.addFlashAttribute("institution", institution);
            return "admin/institution";
        }
        institutionService.update(institution);
        return "redirect:../list";
    }

}
