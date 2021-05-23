package graduation_project.smart_attendance.domain;

import graduation_project.smart_attendance.dto.AddAttendDateDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendDate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attend_date_id")
    private Long id;

    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "attendDate", cascade = CascadeType.MERGE)
    private List<AttendCheck> attendChecks = new ArrayList<>();

    public void setCourse(Course course) {
        this.course = course;
        course.getAttendDates().add(this);
    }

    public static AttendDate createAttendDate(AddAttendDateDto addAttendDateDto, Course course){
        AttendDate attendDate = addAttendDateDto.toEntity();
        attendDate.setCourse(course);
        return attendDate;
    }

}
