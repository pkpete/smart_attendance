package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.domain.AttendDate;
import graduation_project.smart_attendance.domain.Member;
import graduation_project.smart_attendance.repository.AttendDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AttendDateController {
    private final AttendDateRepository attendDateRepository;

    @GetMapping("/user/course/{cId}/dates")
    public String check(@PathVariable("cId") Long courseId, Model model){
        List<AttendDate> attendDates = attendDateRepository.findAttendDatesByCourse_Id(courseId);
        Collections.sort(attendDates);
        model.addAttribute("attendDates", attendDates);
        model.addAttribute("courseId", courseId);
        return "date_selection";
    }
}
