package graduation_project.smart_attendance.domain;

import graduation_project.smart_attendance.dto.AddMemberDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String number;
    private String name;
    private Long age;
    private String classname;

    @Column(nullable = false)
    private String origFilename;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String filepath;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public void setAccount(Account account) {
        this.account = account;
        account.getMembers().add(this);
    }

    public static Member createMember(AddMemberDto addMemberDto, Account account){
        Member member = addMemberDto.toEntity();
        member.setAccount(account);
        return member;
    }
}
