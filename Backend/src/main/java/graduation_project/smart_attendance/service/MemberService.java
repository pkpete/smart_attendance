package graduation_project.smart_attendance.service;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.Course;
import graduation_project.smart_attendance.domain.Member;
import graduation_project.smart_attendance.dto.AddMemberDto;
import graduation_project.smart_attendance.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return memberRepository.findMembers(courseId);
    }

    @Transactional
    public Long member(AddMemberDto addMemberDto, Course course){
        Member member = Member.createMember(addMemberDto, course);
        memberRepository.save(member);
        return member.getId();
    }

//    @Transactional
//    public void updateMember(Long id, AddMemberDto addMemberDto){
//        Member findMember = memberRepository.getOne(id);
//
//        findMember.setNumber(addMemberDto.getNumber());
//        findMember.setName(addMemberDto.getName());
//        findMember.setAge(addMemberDto.getAge());
//        findMember.setClassname(addMemberDto.getClassname());
//        findMember.setOrigFilename(addMemberDto.getOrigFilename());
//        findMember.setFilename(addMemberDto.getFilename());
//        findMember.setFilepath(addMemberDto.getFilepath());
//    }
}
