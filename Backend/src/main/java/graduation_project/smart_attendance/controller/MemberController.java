package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.adapter.UserAccount;
import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.Course;
import graduation_project.smart_attendance.domain.Member;
import graduation_project.smart_attendance.dto.AddCourseDto;
import graduation_project.smart_attendance.dto.AddMemberDto;
import graduation_project.smart_attendance.repository.MemberRepository;
import graduation_project.smart_attendance.service.AccountService;
import graduation_project.smart_attendance.service.CourseService;
import graduation_project.smart_attendance.service.MemberService;
import graduation_project.smart_attendance.service.MemberValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberValidator memberValidator;
    private final MemberRepository memberRepository;
    private final AccountService accountService;
    private final CourseService courseService;

    @GetMapping("/user/course/{cId}/members")
    public String memberList(@PathVariable("cId") Long courseId, Model model){
        List<Member> members = memberService.findMembers(courseId);
        model.addAttribute("members", members);
        model.addAttribute("courseId", courseId);

        return "members";
    }

    @GetMapping("/user/course/{cId}/members/add")
    public String addMemberForm(@PathVariable("cId") Long courseId, Model model){
        model.addAttribute("addMemberDto", new AddMemberDto());
        model.addAttribute("courseId", courseId);
        model.addAttribute("result", null);
        return "popup";
    }

    @PostMapping("/user/course/{cId}/members/add")
    public String addMember(@PathVariable("cId") Long courseId, @RequestParam("memberPic") MultipartFile file, @Valid AddMemberDto addMemberDto, BindingResult bindingResult, Model model){
        Account account = accountService.CurrentAccount();
        Course course = courseService.findCourse(courseId);
        addMemberDto.setCourse(course);
        memberValidator.validate(addMemberDto, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("addMemberDto", new AddMemberDto());
            model.addAttribute("courseId", courseId);
            model.addAttribute("result", bindingResult);
            return "popup";
        }

//        try {
//            String baseDir = "C:\\Users\\kgsmy\\OneDrive\\문서\\attendance_image\\" + account.getId() + "\\" + courseId;
//
//            if(!new File(baseDir).exists()){
//                try{
//                    new File(baseDir).mkdir();
//                }catch (Exception e){
//                    e.getStackTrace();
//                }
//            }
//
//            String filePath = baseDir + "\\" + addMemberDto.getNumber() + ".jpg";
//            file.transferTo(new File(filePath));
//
//            addMemberDto.setOrigFilename(file.getOriginalFilename());
//            addMemberDto.setFilename(addMemberDto.getName());
//            addMemberDto.setFilepath(filePath);
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        memberService.member(addMemberDto, course);

        return "redirect:/user/members/close";

    }

    @GetMapping("/user/course/{cId}/members/{mId}/delete")
    public String deleteMember(@PathVariable("cId") Long courseId, @PathVariable("mId") Long memberId){
        memberService.deleteMember(memberId);
        return "redirect:/user/course/" + courseId.toString() + "/members";
    }

    @GetMapping("/user/members/close")
    public String close(Model model){
        return "close";
    }

    @GetMapping("/user/course/{cId}/member/{mId}/update")
    public String updateMember(@PathVariable("cId") Long courseId, @PathVariable("mId") Long memberId, Model model){
        model.addAttribute("addMemberDto", new AddMemberDto());
        model.addAttribute("courseId", courseId);
        model.addAttribute("memberId", memberId);
        model.addAttribute("result", null);
        return "updatePopup";
    }

    @PostMapping("/user/course/{cId}/member/{mId}/update")
    public String editMember(@PathVariable("cId") Long courseId, @PathVariable("mId") Long memberId, @Valid AddMemberDto addMemberDto, @RequestParam("memberPic") MultipartFile file, BindingResult bindingResult, Model model){
        Account account = accountService.CurrentAccount();
        Course course = courseService.findCourse(courseId);
        addMemberDto.setCourse(course);
        memberValidator.validate(addMemberDto, bindingResult);
        if (bindingResult.hasErrors()){
            model.addAttribute("addMemberDto", new AddMemberDto());
            model.addAttribute("courseId", courseId);
            model.addAttribute("memberId", memberId);
            model.addAttribute("result", bindingResult);
            return "popup";
        }

//        try {
//            String baseDir = "C:\\Users\\kgsmy\\OneDrive\\문서\\attendance_image\\" + account.getId() + "\\" + courseId;
//
//            if(!new File(baseDir).exists()){
//                try{
//                    new File(baseDir).mkdir();
//                }catch (Exception e){
//                    e.getStackTrace();
//                }
//            }
//
//            String filePath = baseDir + "\\" + addMemberDto.getNumber() + ".jpg";
//            file.transferTo(new File(filePath));
//
//            addMemberDto.setOrigFilename(file.getOriginalFilename());
//            addMemberDto.setFilename(addMemberDto.getName());
//            addMemberDto.setFilepath(filePath);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        memberService.updateMember(addMemberDto, course, memberId);
        return "redirect:/user/members/close";
    }
}
