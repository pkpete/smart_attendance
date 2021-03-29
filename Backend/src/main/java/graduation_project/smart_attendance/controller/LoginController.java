package graduation_project.smart_attendance.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String login(@RequestParam(value = "error", required = false) String error,
                        Model model)
    {
        model.addAttribute("error", error);
        return "index";
    }

}
