package graduation_project.smart_attendance.repository;

import graduation_project.smart_attendance.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepository {

    private final EntityManager em;

    public void save(Account account){
        em.persist(account);
    }

    public Account findOne(Long id){
        return em.find(Account.class, id);
    }

    public List<Account> findAll(){
        return em.createQuery("select a from Account a", Account.class)
                .getResultList();
    }

    public List<Account> findByUsername(String username){
        return em.createQuery("select a from Account a where a.username = :username", Account.class)
                .setParameter("username", username)
                .getResultList();
    }
}
