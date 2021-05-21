package graduation_project.smart_attendance.service;

import graduation_project.smart_attendance.domain.AttendCheck;
import graduation_project.smart_attendance.domain.AttendStatus;
import graduation_project.smart_attendance.repository.AttendCheckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttendCheckService {

    private final AttendCheckRepository attendCheckRepository;

    @Transactional
    public void updateCheck(Long attendCheckId, AttendStatus attendStatus){
        AttendCheck attendCheck = attendCheckRepository.findById(attendCheckId).get();
        attendCheck.setAttendCheck(attendStatus);
    }

}
