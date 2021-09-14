package graduation_project.smart_attendance.repository;

import graduation_project.smart_attendance.domain.AttendCheck;
import graduation_project.smart_attendance.domain.AttendDate;
import graduation_project.smart_attendance.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendCheckRepository extends JpaRepository<AttendCheck, Long> {
    List<AttendCheck> findAttendChecksByAttendDate_Id(Long attendDateId);
    AttendCheck findAttendCheckByMemberAndAttendDate(Member member, AttendDate attendDate);
}
