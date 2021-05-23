package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.AttendDate;
import graduation_project.smart_attendance.domain.Course;
import graduation_project.smart_attendance.dto.SwSaveCheckDto;
import graduation_project.smart_attendance.dto.SwSaveDateDto;
import graduation_project.smart_attendance.repository.AccountRepository;
import graduation_project.smart_attendance.repository.CourseRepository;
import graduation_project.smart_attendance.service.AccountService;
import graduation_project.smart_attendance.service.AttendCheckService;
import graduation_project.smart_attendance.service.AttendDateService;
import graduation_project.smart_attendance.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SwDateNCheckController {

    private final AccountRepository accountRepository;
    private final AttendDateService attendDateService;
    private final AttendCheckService attendCheckService;
    private final CourseService courseService;

    @PostMapping("sw/date")
    public String date(@RequestBody SwSaveDateDto swSaveDateDto){
        Account account = accountRepository.findById(swSaveDateDto.getProfID()).get();
        Course course = courseService.findCourseByName(account, swSaveDateDto.getCourse());
        Long attendDateId = attendDateService.saveDate(course);
        attendCheckService.saveChecks(attendDateId, course);
        return "True";
    }

    @PostMapping("sw/check")
    public String check(@RequestBody SwSaveCheckDto swSaveCheckDto){
        System.out.println("start:");
        attendCheckService.updateCheck(swSaveCheckDto);
        return "True";
    }
}
