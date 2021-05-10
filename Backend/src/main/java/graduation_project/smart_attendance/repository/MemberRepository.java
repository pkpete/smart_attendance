package graduation_project.smart_attendance.repository;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.Course;
import graduation_project.smart_attendance.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByNumberAndCourse(String number, Course course);

    @Query(value = "select DISTINCT m from Member m join m.course c on c.id = :courseId")
    List<Member> findMembers(@Param("courseId") Long courseId);
}
