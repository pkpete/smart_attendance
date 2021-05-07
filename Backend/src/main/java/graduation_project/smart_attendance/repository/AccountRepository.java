package graduation_project.smart_attendance.repository;

import graduation_project.smart_attendance.domain.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);
    Optional<Account> findByUsernameAndPassword(String username, String Password);
}
