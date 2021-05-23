package graduation_project.smart_attendance.service;

import graduation_project.smart_attendance.domain.Member;
import graduation_project.smart_attendance.dto.AccountDto;
import graduation_project.smart_attendance.dto.AddMemberDto;
import graduation_project.smart_attendance.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class MemberValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AddMemberDto.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        AddMemberDto addMemberDto = (AddMemberDto) obj;
        if(memberRepository.findByNumberAndCourse(addMemberDto.getNumber(), addMemberDto.toEntity().getCourse()) != null){
            errors.rejectValue("number", "key");
        }
    }

    public void updateValidate(Object obj, Errors errors, Long memberId) {
        AddMemberDto addMemberDto = (AddMemberDto) obj;
        if(memberRepository.findById(memberId).get().getNumber().equals(addMemberDto.getNumber())){
            return;
        }
        if(memberRepository.findByNumberAndCourse(addMemberDto.getNumber(), addMemberDto.toEntity().getCourse()) != null){
            errors.rejectValue("number", "key");
        }
    }

}
