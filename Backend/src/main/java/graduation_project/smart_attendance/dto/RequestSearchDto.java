package graduation_project.smart_attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestSearchDto {
    private String accountUsername;
    private String choice;
    private String search;
}
