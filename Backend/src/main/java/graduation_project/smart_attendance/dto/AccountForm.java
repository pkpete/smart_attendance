package graduation_project.smart_attendance.dto;

import graduation_project.smart_attendance.Hashing;
import graduation_project.smart_attendance.domain.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AccountForm {
    @NotNull(message = "아이디는 5자 이상입니다.")
    @Length(min = 5)
    private String username;
    @NotNull(message = "비밀번호는 8자 이상입니다.")
    @Length(min = 8)
    private String password;
    @NotNull
    @Length(min = 8)
    private String confirmPassword;
    @NotNull(message = "이름을 입력해주세요.")
    private String name;
    @NotNull(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞춰주세요.")
    private String email;

}
