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
        if(requestSearchDto.getSearch().equals("course")){
            return memberService.findMembersByCourseName(requestSearchDto.getProfID(), requestSearchDto.getSearch());
        }else if (requestSearchDto.getSearch().equals("id")){
            return memberService.findMembersLikeName(requestSearchDto.getProfID(), requestSearchDto.getSearch());
        }
        else {
            return null;
        }
    }
}
