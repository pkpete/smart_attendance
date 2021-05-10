package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.Course;
import graduation_project.smart_attendance.dto.AddCourseDto;
import graduation_project.smart_attendance.service.AccountService;
import graduation_project.smart_attendance.service.CourseService;
import graduation_project.smart_attendance.service.CourseValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CourseController {

    private final AccountService accountService;
    private final CourseService courseService;
    private final CourseValidator courseValidator;

    @GetMapping("/user/courses")
    public String courseList(Model model){
        Account account = accountService.CurrentAccount();
        List<Course> courses = courseService.findCourses(account);
        model.addAttribute("courses", courses);
        model.addAttribute("addCourseDto", new AddCourseDto());

        return "class_selection";
    }

    @PostMapping("/user/courses")
    public String addCourse(@Valid AddCourseDto addCourseDto, BindingResult bindingResult){
        Account account = accountService.CurrentAccount();
        addCourseDto.setAccount(account);
        courseValidator.validate(addCourseDto, bindingResult);
        if(bindingResult.hasErrors()){
            return "class_selection";
        }

        courseService.course(addCourseDto, account);

        return "redirect:/user/courses";
    }

    @GetMapping("/user/courses/close")
    public String close(Model model){
        return "close";
    }
}
