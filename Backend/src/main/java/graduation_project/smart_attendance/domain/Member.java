package graduation_project.smart_attendance.domain;

import graduation_project.smart_attendance.dto.AddMemberDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member implements Comparable<Member>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String number;
    private String name;
    private Long age;
//
//    private String origFilename;
//
//    private String filename;
//
//    private String filepath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttendCheck> attendChecks = new ArrayList<>();

    public void setCourse(Course course) {
        this.course = course;
        course.getMembers().add(this);
    }

    public static Member createMember(AddMemberDto addMemberDto, Course course){
        Member member = addMemberDto.toEntity();
        member.setCourse(course);
        return member;
    }

    @Override
    public int compareTo(Member o) {
        return this.number.compareTo(o.number);
    }
}
