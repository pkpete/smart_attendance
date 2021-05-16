package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.dto.RequestSearchDto;
import graduation_project.smart_attendance.dto.SearchMemberDto;
import graduation_project.smart_attendance.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseRestController {

    private final MemberService memberService;

    @PostMapping("/sw/search-members")
    public List<SearchMemberDto> searchMember(@RequestBody RequestSearchDto requestSearchDto){
        System.out.println(requestSearchDto.getAccountUsername() +" "+ requestSearchDto.getChoice() + " " + requestSearchDto.getSearch());
        if(requestSearchDto.getChoice().equals("course")){
            return memberService.findMembersByCourseName(requestSearchDto.getAccountUsername(), requestSearchDto.getSearch());
        }else if (requestSearchDto.getChoice().equals("id")){
            return memberService.findMembersLikeName(requestSearchDto.getAccountUsername(), requestSearchDto.getSearch());
        }
        else {
            return null;
        }
    }
}
