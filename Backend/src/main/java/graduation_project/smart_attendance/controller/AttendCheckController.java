package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.domain.AttendCheck;
import graduation_project.smart_attendance.domain.AttendStatus;
import graduation_project.smart_attendance.dto.AttendCheckDto;
import graduation_project.smart_attendance.repository.AttendCheckRepository;
import graduation_project.smart_attendance.service.AttendCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AttendCheckController {

    private final AttendCheckRepository attendCheckRepository;
    private final AttendCheckService attendCheckService;

    @GetMapping("/user/course/{cId}/date/{dId}/checks")
    public String checks(@PathVariable("cId") Long courseId, @PathVariable("dId") Long dateId, Model model){
        List<AttendCheck> attendChecks = attendCheckRepository.findAttendChecksByAttendDate_Id(dateId);
        AttendCheckDto attendCheckDto = new AttendCheckDto();
        attendCheckDto.setAttendChecks(attendChecks);
        model.addAttribute("attendCheckDto", attendCheckDto);
        model.addAttribute("courseId", courseId);
        model.addAttribute("dateId", dateId);
        model.addAttribute("status", AttendStatus.values());
        return "check";
    }

    @PostMapping("/user/course/{cId}/date/{dId}/checks")
    public String updateCheck(@PathVariable("cId") Long courseId, @PathVariable("dId") Long dateId, AttendCheckDto attendCheckDto){
        System.out.println(attendCheckDto);
        List<AttendCheck> attendChecks = attendCheckRepository.findAttendChecksByAttendDate_Id(dateId);
        for(int i = 0; i < attendChecks.size(); i++){
            attendChecks.get(i).setAttendCheck(attendCheckDto.getAttendChecks().get(i).getAttendCheck());
        }
        attendCheckService.updateChecks(attendChecks);
        return "redirect:/user/course/{cId}/date/{dId}/checks";
    }
}
