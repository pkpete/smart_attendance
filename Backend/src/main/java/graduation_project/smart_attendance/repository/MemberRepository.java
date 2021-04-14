package graduation_project.smart_attendance.repository;

import graduation_project.smart_attendance.domain.Account;
import graduation_project.smart_attendance.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByNumberAndAccount(String number, Account account);

    @Query(value = "select DISTINCT m from Member m join m.account a on a.username = :username")
    List<Member> findMembers(@Param("username") String username);
}
