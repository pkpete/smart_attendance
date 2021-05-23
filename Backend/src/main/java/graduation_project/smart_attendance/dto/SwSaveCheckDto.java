package graduation_project.smart_attendance.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SwSaveCheckDto {
    private Long profID;
    private String course;
    private String studentID;
    private String date;
    private String time;
}
