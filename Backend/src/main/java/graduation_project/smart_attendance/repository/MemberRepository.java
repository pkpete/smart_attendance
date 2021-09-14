package graduation_project.smart_attendance.repository;

import graduation_project.smart_attendance.domain.Course;
import graduation_project.smart_attendance.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByNumberAndCourse(String number, Course course);

    List<Member> findMembersByCourse_Id(Long courseId);

    List<Member> findMembersByCourse_Account_IdAndCourse_CourseName(Long username, String courseName);

    List<Member> findMembersByCourse_Account_IdAndNumberContaining(Long username, String memberNumber);
}
