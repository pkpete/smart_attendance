package graduation_project.smart_attendance.service;

import graduation_project.smart_attendance.domain.*;
import graduation_project.smart_attendance.dto.SwSaveCheckDto;
import graduation_project.smart_attendance.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
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
    public void updateChecks(List<AttendCheck> attendChecks){
        for(AttendCheck attendCheck : attendChecks){
            attendCheckRepository.save(attendCheck);
        }
    }

    @Transactional
    public void saveChecks(Long attendDateId, Course course){
        List<Member> members = memberRepository.findMembersByCourse_Id(course.getId());
        for(Member member : members){
            AttendDate attendDate = attendDateRepository.findById(attendDateId).get();
            AttendCheck attendCheck = AttendCheck.createAttendCheck(member, attendDate);
            attendCheckRepository.save(attendCheck);
        }
    }

    @Transactional
    public void updateCheck(SwSaveCheckDto swSaveCheckDto){
        Account account = accountRepository.findById(swSaveCheckDto.getProfID()).get();
        Course course = courseRepository.findByCourseNameAndAccount(swSaveCheckDto.getCourse(), account);
        LocalDate date = LocalDate.parse(swSaveCheckDto.getDate());
        AttendDate attendDate = attendDateRepository.findByDateAndCourse(date, course);
        Member member = memberRepository.findByNumberAndCourse(swSaveCheckDto.getStudentID(), course);
        AttendCheck attendCheck = attendCheckRepository.findAttendCheckByMemberAndAttendDate(member, attendDate);
        attendCheck.setAttendCheck(AttendStatus.출석);
        attendCheckRepository.save(attendCheck);
    }
}
