package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.Course;
import graduation_project.smart_attendance.domain.Member;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
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
        Collections.sort(courses);
        Long courseId = 0L;
        model.addAttribute("courses", courses);
        model.addAttribute("addCourseDto", new AddCourseDto());
        model.addAttribute("courseId", courseId);

        return "class_selection";
    }

    @PostMapping("/user/courses")
    public String addCourse(@Valid AddCourseDto addCourseDto, BindingResult bindingResult){
        System.out.println(addCourseDto.getStartTime());
        Account account = accountService.CurrentAccount();
        addCourseDto.setAccount(account);
        courseValidator.validate(addCourseDto, bindingResult);
        if(bindingResult.hasErrors()){
            return "class_selection";
        }

        courseService.course(addCourseDto, account);

        return "redirect:/user/courses";
    }

    @GetMapping("/user/course/{cId}/delete")
    public String deleteResponse(@PathVariable("cId") Long courseId, Model model){
        Account account = accountService.CurrentAccount();
        List<Course> courses = courseService.findCourses(account);
        Collections.sort(courses);
        model.addAttribute("courses", courses);
        model.addAttribute("addCourseDto", new AddCourseDto());
        model.addAttribute("courseId", courseId);
        return "class_selection";
    }

    @GetMapping("/user/course/{cId}/delete/reset")
    public String delete(@PathVariable("cId") Long courseId){
        courseService.deleteCourse(courseId);
        return "redirect:/user/courses";
    }

    @GetMapping("/user/courses/close")
    public String close(Model model){
        return "close";
    }
}
