package graduation_project.smart_attendance.service;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.Member;
import graduation_project.smart_attendance.dto.AddMemberDto;
import graduation_project.smart_attendance.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findMembers(Account account){
        return memberRepository.findMembers(account.getUsername());
    }

    @Transactional
    public Long member(AddMemberDto addMemberDto, Account account){
        Member member = Member.createMember(addMemberDto, account);
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
