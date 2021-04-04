package graduation_project.smart_attendance.controller;

import graduation_project.smart_attendance.adapter.UserAccount;
import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.Member;
import graduation_project.smart_attendance.dto.AddMemberDto;
import graduation_project.smart_attendance.repository.MemberRepository;
import graduation_project.smart_attendance.service.AccountService;
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

    @GetMapping("/user/members")
    public String memberList(Model model){
        Account account = accountService.CurrentAccount();
        List<Member> members = memberService.findMembers(account);
        model.addAttribute("members", members);

        return "members";
    }

    @GetMapping("/user/members/add")
    public String addMemberForm(Model model){
        model.addAttribute("addMemberDto", new AddMemberDto());
        return "popup";
    }

    @PostMapping("/user/members/add")
    public String addMember(@RequestParam("memberPic") MultipartFile file, @Valid AddMemberDto addMemberDto, BindingResult bindingResult){
        memberValidator.validate(addMemberDto, bindingResult);
        if (bindingResult.hasErrors()){
            return "popup";
        }
        Account account = accountService.CurrentAccount();

        try {
            String baseDir = "C:\\Users\\kgsmy\\OneDrive\\문서\\attendance_image";
            String filePath = baseDir + "\\" + addMemberDto.getName() + ".jpg";
            file.transferTo(new File(filePath));

            addMemberDto.setOrigFilename(file.getOriginalFilename());
            addMemberDto.setFilename(addMemberDto.getName());
            addMemberDto.setFilepath(filePath);

            memberService.member(addMemberDto, account);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/user/members/close";

    }

    @GetMapping("/user/members/close")
    public String close(Model model){
        return "close";
    }

//    @GetMapping("/user/{id}/edit")
//    public String editMemberForm(@PathVariable("id") Long id, Model model){
//        Member member = memberRepository.getOne(id);
//
//        AddMemberDto addMemberDto = new AddMemberDto();
//        addMemberDto.setId(member.getId());
//        addMemberDto.setNumber(member.getNumber());
//        addMemberDto.setName(member.getName());
//        addMemberDto.setAge(member.getAge());
//        addMemberDto.setClassname(member.getClassname());
//        addMemberDto.setOrigFilename(member.getOrigFilename());
//        addMemberDto.setFilename(member.getFilename());
//        addMemberDto.setFilepath(member.getFilepath());
//        addMemberDto.setAccount(member.getAccount());
//
//        model.addAttribute("addMemberDto", addMemberDto);
//        return "updatePopup";
//    }
//
//    @PostMapping("/user/{id}/edit")
//    public String editMember(@PathVariable Long id, @ModelAttribute AddMemberDto addMemberDto){
//
//        memberService.updateMember(id, addMemberDto);
//
//        return "redirect:/user/members/close";
//    }
}
