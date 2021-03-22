package graduation_project.smart_attendance.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    @GetMapping("members/new")
    public String createForm(Model model){
        model.addAttribute("MemberForm", new MemberForm());
        return "members/signup";
    }

}
