package graduation_project.smart_attendance.dto;

import graduation_project.smart_attendance.domain.AttendDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddAttendDateDto {
    private String attendDate;

    public AttendDate toEntity(){
        return AttendDate.builder()
                .attendDate(attendDate)
                .build();
    }
}
