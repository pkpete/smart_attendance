package graduation_project.smart_attendance.repository;

import graduation_project.smart_attendance.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }

    public void findUsername(String username){
        em.find(Member.class, username);
    }
}
