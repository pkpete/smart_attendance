package graduation_project.smart_attendance.service;

import graduation_project.smart_attendance.dto.AccountForm;
import graduation_project.smart_attendance.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class AccountValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountForm.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        AccountForm accountForm = (AccountForm) obj;
        if(!((AccountForm) obj).getPassword().equals(((AccountForm) obj).getConfirmPassword())){
            errors.rejectValue("password", "key", "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
        } else if(accountRepository.findByUsername(((AccountForm) obj).getUsername()) != null){
            errors.rejectValue("username", "key", "이미 존재하는 아이디입니다.");
        }
    }
}