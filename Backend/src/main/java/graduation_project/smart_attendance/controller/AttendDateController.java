package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.domain.AttendDate;
import graduation_project.smart_attendance.domain.Member;
import graduation_project.smart_attendance.repository.AttendDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AttendDateController {
    private final AttendDateRepository attendDateRepository;

    @GetMapping("/user/course/{cId}/dates")
    public String check(@PathVariable("cId") Long courseId, Model model){
        List<AttendDate> attendDates = attendDateRepository.findAttendDatesByCourse_Id(courseId);
        attendDates.sort(new Comparator<AttendDate>() {
            @Override
            public int compare(AttendDate o1, AttendDate o2) {
                Long num1 = Long.parseLong(o1.getDate().toString());
                Long num2 = Long.parseLong(o2.getDate().toString());
                if(num1 == num2) return 0;
                else if(num1 > num2) return 1;
                else return -1;
            }
        });
        model.addAttribute("attendDates", attendDates);
        model.addAttribute("courseId", courseId);
        return "date_selection";
    }
}
