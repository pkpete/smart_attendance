package graduation_project.smart_attendance.service;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.dto.AccountForm;
import graduation_project.smart_attendance.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Long createUser(Account account) {

        accountRepository.save(account);
        return account.getId();
    }

    public void validate(AccountForm accountForm, Errors errors) {
        List<Account> findMembers = accountRepository.findByUsername(accountForm.getUsername());

        if(!accountForm.getPassword().equals(accountForm.getConfirmPassword())){
            errors.rejectValue("confirmPassword", "key","비밀번호가 일치하지 않습니다.");

        }
        else if(!findMembers.isEmpty()){
            errors.rejectValue("username", "key","이미 사용자 이름이 존재합니다.");
        }

    }
}
