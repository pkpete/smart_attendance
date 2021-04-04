package graduation_project.smart_attendance.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String role;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Member> members = new ArrayList<>();

}
