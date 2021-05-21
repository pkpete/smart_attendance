package graduation_project.smart_attendance.service;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.AttendCheck;
import graduation_project.smart_attendance.domain.AttendStatus;
import graduation_project.smart_attendance.dto.SwSaveCheckDto;
import graduation_project.smart_attendance.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttendCheckService {

    private final AccountRepository accountRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;
    private final AttendDateRepository attendDateRepository;
    private final AttendCheckRepository attendCheckRepository;

    @Transactional
    public void updateCheck(Long attendCheckId, AttendStatus attendStatus){
        AttendCheck attendCheck = attendCheckRepository.findById(attendCheckId).get();
        attendCheck.setAttendCheck(attendStatus);
    }

    @Transactional
    public void saveCheck(SwSaveCheckDto swSaveCheckDto){
        Account account = accountRepository.findById(swSaveCheckDto.getProfID()).get();
        courseRepository.findByCourseNameAndAccount(swSaveCheckDto.getCourse(), account);

    }
}
