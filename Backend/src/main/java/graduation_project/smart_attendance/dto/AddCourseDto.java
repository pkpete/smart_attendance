package graduation_project.smart_attendance.dto;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCourseDto {

    private String courseName;
    private Account account;

    public Course toEntity(){
        return Course.builder()
                .courseName(courseName)
                .account(account)
                .build();
    }
}
