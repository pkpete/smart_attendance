package graduation_project.smart_attendance.domain;

import graduation_project.smart_attendance.dto.AddCourseDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Course implements Comparable<Course>{
    @Id @GeneratedValue
    @Column(name = "course_id")
    private Long id;
    private String courseName;

    private String startTime;
    private String endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
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

    @Override
    public int compareTo(Course o) {
        return this.courseName.compareTo(o.courseName);
    }
}
