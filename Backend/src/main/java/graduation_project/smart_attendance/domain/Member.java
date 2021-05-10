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
    @JoinColumn(name = "course_id")
    private Course course;

    public void setCourse(Course course) {
        this.course = course;
        course.getMembers().add(this);
    }

    public static Member createMember(AddMemberDto addMemberDto, Course course){
        Member member = addMemberDto.toEntity();
        member.setCourse(course);
        return member;
    }
}
