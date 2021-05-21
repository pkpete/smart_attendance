package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.dto.SwSaveCheckDto;
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
    public String date(@RequestBody SwSaveDateDto swSaveDateDto){
        attendDateService.saveDate(swSaveDateDto.getProfID(), swSaveDateDto.getCourse());
        return "True";
    }

    @PostMapping("sw/check")
    public String check(@RequestBody SwSaveCheckDto swSaveCheckDto){
        System.out.println("save: " + swSaveCheckDto);
        return "True";
    }
}
