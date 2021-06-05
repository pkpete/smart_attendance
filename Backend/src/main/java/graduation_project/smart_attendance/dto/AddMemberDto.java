package graduation_project.smart_attendance.dto;

import graduation_project.smart_attendance.domain.Course;
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

    private String origFilename;

    private String filename;

    private String filepath;

    private Course course;

    public Member toEntity(){
        return Member.builder()
                .number(number)
                .name(name)
                .age(age)
//                .origFilename(origFilename)
//                .filename(filename)
//                .filepath(filepath)
                .course(course)
                .build();
    }
}
