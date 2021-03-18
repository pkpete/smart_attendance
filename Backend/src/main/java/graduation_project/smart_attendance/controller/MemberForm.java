package graduation_project.smart_attendance.controller;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class MemberForm {

    @NotBlank(message = "Id는 5자 이상입니다!")
    @Length(min = 5)
    private String username;

    @NotBlank(message = "비밀번호는 5자 이상입니다!")
    @Length(min = 5)
    private String password;

    @NotBlank
    private String confirmPassword;

}
