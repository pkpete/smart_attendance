package graduation_project.smart_attendance.service;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.Course;
import graduation_project.smart_attendance.domain.Member;
import graduation_project.smart_attendance.dto.AddMemberDto;
import graduation_project.smart_attendance.dto.FindAccountDto;
import graduation_project.smart_attendance.dto.SearchMemberDto;
import graduation_project.smart_attendance.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findMember(Long memberId){
        return memberRepository.findById(memberId).get();
    }

    public List<Member> findMembers(Long courseId){
        return memberRepository.findMembersByCourse_Id(courseId);
    }

    public List<SearchMemberDto> findMembersByCourseName(String username, String courseName){
        List<Member> members = memberRepository.findMembersByCourse_Account_UsernameAndCourse_CourseName(username, courseName);
        List<SearchMemberDto> searchMemberDtos= new ArrayList<>();
        members.forEach(x -> searchMemberDtos.add(new SearchMemberDto(x.getNumber(), x.getName(), x.getCourse().getCourseName())));
        return searchMemberDtos;
    }

    public List<SearchMemberDto> findMembersLikeName(String username, String search){
        List<Member> members = memberRepository.findMembersByCourse_Account_UsernameAndNumberContaining(username, search);
        System.out.println("username " + username + "search:" + search);
        System.out.println("find: " + members);
        List<SearchMemberDto> searchMemberDtos= new ArrayList<>();
        members.forEach(x -> searchMemberDtos.add(new SearchMemberDto(x.getNumber(), x.getName(), x.getCourse().getCourseName())));
        return searchMemberDtos;
    }

    @Transactional
    public Long member(AddMemberDto addMemberDto, Course course){
        Member member = Member.createMember(addMemberDto, course);
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public Long updateMember(AddMemberDto addMemberDto, Course course, Long memberId){
        Member member = Member.createMember(addMemberDto, course);
        member.setId(memberId);
        memberRepository.save(member);
        return member.getId();
    }

    @Transactional
    public void deleteMember(Long memberId){
        memberRepository.deleteById(memberId);
    }

    @Transactional
    public void updateMember(Long memberId){

    }
}
