package graduation_project.smart_attendance.domain;

import graduation_project.smart_attendance.dto.AddAttendCheckDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendCheck {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attend_check_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private AttendStatus attendCheck;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attend_date_id")
    private AttendDate attendDate;

    public void setMember(Member member) {
        this.member = member;
        member.getAttendChecks().add(this);
    }

    public void setAttendDate(AttendDate attendDate) {
        this.attendDate = attendDate;
        attendDate.getAttendChecks().add(this);
    }

    public static AttendCheck createAttendCheck(AddAttendCheckDto addAttendCheckDto, Member member, AttendDate attendDate){
        AttendCheck attendCheck = addAttendCheckDto.toEntity();
        attendCheck.setMember(member);
        attendCheck.setAttendDate(attendDate);
        return attendCheck;
    }
}
