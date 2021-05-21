package graduation_project.smart_attendance.repository;

import graduation_project.smart_attendance.domain.AttendCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendCheckRepository extends JpaRepository<AttendCheck, Long> {
    List<AttendCheck> findAttendChecksByAttendDate_Id(Long attendDateId);
}
