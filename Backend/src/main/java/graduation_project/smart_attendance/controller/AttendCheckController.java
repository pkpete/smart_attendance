package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.domain.AttendCheck;
import graduation_project.smart_attendance.repository.AttendCheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AttendCheckController {

    private final AttendCheckRepository attendCheckRepository;

    @GetMapping("/user/course/{cId}/date/{dId}/checks")
    public String checks(@PathVariable("cId") Long courseId, @PathVariable("dId") Long dateId, Model model){
        List<AttendCheck> attendChecks = attendCheckRepository.findAttendChecksByAttendDate_Id(dateId);
        model.addAttribute("attendChecks", attendChecks);
        model.addAttribute("courseId", courseId);
        model.addAttribute("dateId", dateId);
        return "check";
    }
}
