package graduation_project.smart_attendance.domain;

import graduation_project.smart_attendance.dto.AddAttendCheckDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttendCheck implements Comparable<AttendCheck>{
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
        System.out.println("member.getAttendChecks() = " + member.getAttendChecks());
        member.getAttendChecks().add(this);
    }

    public void setAttendDate(AttendDate attendDate) {
        this.attendDate = attendDate;
        System.out.println("attendDate.getAttendChecks() = " + attendDate.getAttendChecks());
        attendDate.getAttendChecks().add(this);
    }

    public static AttendCheck createAttendCheck(Member member, AttendDate attendDate){
        AttendCheck attendCheck = new AttendCheck();
        attendCheck.setAttendCheck(AttendStatus.결석);
        attendCheck.setMember(member);
        attendCheck.setAttendDate(attendDate);
        return attendCheck;
    }

    @Override
    public int compareTo(AttendCheck o) {
        return this.member.getNumber().compareTo(o.getMember().getNumber());
    }
}
