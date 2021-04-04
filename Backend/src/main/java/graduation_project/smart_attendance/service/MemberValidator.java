package graduation_project.smart_attendance.service;

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
        if(memberRepository.findByNumber(((AddMemberDto) obj).getNumber()) != null){
            errors.rejectValue("number", "key", "이미 존재하는 번호입니다.");
        }
    }
}
