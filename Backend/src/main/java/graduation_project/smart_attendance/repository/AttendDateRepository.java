package graduation_project.smart_attendance.repository;

import graduation_project.smart_attendance.domain.AttendDate;
import graduation_project.smart_attendance.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendDateRepository extends JpaRepository<AttendDate, Long> {
    List<AttendDate> findAttendDatesByCourse_Id(Long courseId);

    AttendDate findByDateAndCourse(LocalDate date, Course course);
}
