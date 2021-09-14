package graduation_project.smart_attendance.service;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.AttendDate;
import graduation_project.smart_attendance.domain.Course;
import graduation_project.smart_attendance.dto.AddAttendDateDto;
import graduation_project.smart_attendance.repository.AccountRepository;
import graduation_project.smart_attendance.repository.AttendDateRepository;
import graduation_project.smart_attendance.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AttendDateService {

    private final AccountRepository accountRepository;
    private final CourseRepository courseRepository;
    private final AttendDateRepository attendDateRepository;

    @Transactional
    public Long saveDate(Course course){
        AttendDate attendDate = new AttendDate();
        attendDate.setDate(LocalDate.now());
        attendDate.setCourse(course);
        attendDateRepository.save(attendDate);
        return attendDate.getId();
    }
}
