package graduation_project.smart_attendance.domain;

import graduation_project.smart_attendance.dto.AddAttendDateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendDate {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attend_date_id")
    private Long id;

    private String attendDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "attendDate", cascade = CascadeType.ALL)
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
