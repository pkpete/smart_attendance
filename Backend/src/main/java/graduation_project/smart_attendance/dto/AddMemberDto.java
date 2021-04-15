package graduation_project.smart_attendance.dto;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddMemberDto {
    private String number;
    private String name;
    private Long age;
    private String classname;

    private String origFilename;

    private String filename;

    private String filepath;

    private Account account;

    public Member toEntity(){
        return Member.builder()
                .number(number)
                .name(name)
                .age(age)
                .classname(classname)
                .origFilename(origFilename)
                .filename(filename)
                .filepath(filepath)
                .account(account)
                .build();
    }
}
