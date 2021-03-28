package graduation_project.smart_attendance.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class AttendanceController {

    @GetMapping("/user/attendance")
    public String Attendance(){
        log.info("Attendance Controller");
        return "attendance";
    }
}
