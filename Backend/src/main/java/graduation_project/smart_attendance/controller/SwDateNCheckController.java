package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.dto.SwSaveDateDto;
import graduation_project.smart_attendance.service.AttendDateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SwDateNCheckController {

    private final AttendDateService attendDateService;

    @PostMapping("sw/date")
    public String dateNCheck(@RequestBody SwSaveDateDto swSaveDateDto){
        attendDateService.saveDate(swSaveDateDto.getProfID(), swSaveDateDto.getCourse());
        return "True";
    }
}
