package graduation_project.smart_attendance.dto;

import graduation_project.smart_attendance.domain.AttendCheck;
import graduation_project.smart_attendance.domain.AttendStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddAttendCheckDto {
    private String attendCheck;

    public AttendCheck toEntity(){
        AttendStatus attendStatus;
        if(attendCheck == "1"){
            attendStatus = AttendStatus.출석;
        }else if(attendCheck == "2"){
            attendStatus = AttendStatus.지각;
        }else{
            attendStatus = AttendStatus.결석;
        }

        return AttendCheck.builder()
                .attendCheck(attendStatus)
                .build();
    }
}
