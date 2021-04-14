package graduation_project.smart_attendance.service;

import graduation_project.smart_attendance.dto.AccountDto;
import graduation_project.smart_attendance.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class AccountValidator implements Validator {

    private final AccountRepository accountRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AccountDto.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        AccountDto accountDto = (AccountDto) obj;
        if(!accountDto.getPassword().equals(accountDto.getConfirmPassword())){
            errors.rejectValue("password", "key");
        } else if(accountRepository.findByUsername(accountDto.getUsername()) != null){
            errors.rejectValue("username", "key");
        }
    }
}
