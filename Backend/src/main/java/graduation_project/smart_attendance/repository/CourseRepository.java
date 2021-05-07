package graduation_project.smart_attendance.repository;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByCourseNameAndAccount(String courseName, Account account);

    @Query(value = "select DISTINCT c from Course c join c.account a on a.username = :username")
    List<Course> findCourses(@Param("username") String username);
}
