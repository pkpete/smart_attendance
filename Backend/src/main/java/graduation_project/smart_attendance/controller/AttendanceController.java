package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class AttendanceController {

    private final AccountRepository accountRepository;

    @GetMapping("/user/attendance")
    public String Attendance(){
        log.info("Attendance Controller");
        return "attendance";
    }

}
