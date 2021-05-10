package graduation_project.smart_attendance.service;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.dto.AccountDto;
import graduation_project.smart_attendance.dto.FindAccountDto;
import graduation_project.smart_attendance.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    @Transactional
    public Long createUser(AccountDto accountDto) {
        Account account = accountDto.toEntity();
        account.setRole("ROLE_USER");
        accountRepository.save(account);
        return account.getId();
    }

    public Boolean findUser(FindAccountDto findAccountDto){
        Account account = accountRepository.findByUsername(findAccountDto.getUsername());
        if(account == null){
            return false;
        }
        return new BCryptPasswordEncoder().matches(findAccountDto.getPassword(), account.getPassword());
    }

    public Account CurrentAccount(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = (User) authentication.getPrincipal();

        return accountRepository.findByUsername(user.getUsername());
    }
}
