package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.Hashing;
import graduation_project.smart_attendance.domain.Account;
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
    public String createUser(@Valid AccountForm form, BindingResult bindingResult){
        accountService.validate(form, bindingResult);
        if(bindingResult.hasErrors()) {
            return "signup"; // 실패
        }

            Account account = new Account();
            account.setUsername(form.getUsername());
            account.setPassword(Hashing.hashingPassword(form.getPassword()));
            account.setName(form.getName());
            account.setEmail(form.getEmail());

            accountService.createUser(account);

            return "redirect:/";
    }
}
