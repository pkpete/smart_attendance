package graduation_project.smart_attendance.dto;

import graduation_project.smart_attendance.domain.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AccountForm {

    private Long id;
    private String username;
    private String password;
    private String confirmPassword;
    private String name;
    private String email;
    private String role;

    @Builder
    public AccountForm(Long id, String username, String password, String confirmPassword, String name, String email, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public Account toEntity(){
        return Account.builder()
                .id(id)
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .name(name)
                .email(email)
                .role(role)
                .build();
    }
}
