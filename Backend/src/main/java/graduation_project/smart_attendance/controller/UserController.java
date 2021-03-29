package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.dto.AccountForm;
import graduation_project.smart_attendance.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final AccountService accountService;

    @GetMapping("/signup")
    public String createUserForm(Model model){
        model.addAttribute("userForm", new AccountForm());
        return "signup";
    }

    @PostMapping("/signup")
    public String createUser(@Valid AccountForm form, BindingResult result){
        if(result.hasErrors()){
            return "signup";
        }
        accountService.createUser(form);

        return "redirect:/";
    }
}
