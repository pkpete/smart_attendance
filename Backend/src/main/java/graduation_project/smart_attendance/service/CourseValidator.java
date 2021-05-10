package graduation_project.smart_attendance.service;

import graduation_project.smart_attendance.dto.AddCourseDto;
import graduation_project.smart_attendance.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class CourseValidator implements Validator {
    private final CourseRepository courseRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return AddCourseDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AddCourseDto addCourseDto = (AddCourseDto) target;
        if(courseRepository.findByCourseNameAndAccount(addCourseDto.getCourseName(), addCourseDto.getAccount()) != null){
            errors.rejectValue("courseName", "key");
        }
    }
}
