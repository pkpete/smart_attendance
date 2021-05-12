package graduation_project.smart_attendance.domain;

import graduation_project.smart_attendance.dto.AddCourseDto;
import lombok.*;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Course {
    @Id @GeneratedValue
    @Column(name = "course_id")
    private Long id;
    private String courseName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<AttendDate> attendDates = new ArrayList<>();

    public void setAccount(Account account){
        this.account = account;
        account.getCourses().add(this);
    }

    public static Course createCourse(AddCourseDto addCourseDto, Account account){
        Course course = addCourseDto.toEntity();
        course.setAccount(account);
        return course;
    }
}
