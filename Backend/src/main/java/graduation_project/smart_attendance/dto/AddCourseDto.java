package graduation_project.smart_attendance.dto;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddCourseDto {

    private String courseName;
    private String startTime;
    private String endTime;
    private Account account;

    public Course toEntity(){
        return Course.builder()
                .courseName(courseName)
                .startTime(startTime)
                .endTime(endTime)
                .account(account)
                .build();
    }
}
