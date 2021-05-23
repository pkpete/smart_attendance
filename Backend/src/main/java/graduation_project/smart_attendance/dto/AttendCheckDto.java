package graduation_project.smart_attendance.dto;

import graduation_project.smart_attendance.domain.AttendCheck;
import lombok.Data;

import java.util.List;

@Data
public class AttendCheckDto {
    List<AttendCheck> attendChecks;
}
