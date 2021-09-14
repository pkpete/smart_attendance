package graduation_project.smart_attendance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import graduation_project.smart_attendance.domain.Account;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private String username;
    private String password;
    private String confirmPassword;
    private String name;
    private String email;
    private String role;

    public Account toEntity(){
        return Account.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .name(name)
                .email(email)
                .build();
    }
}
