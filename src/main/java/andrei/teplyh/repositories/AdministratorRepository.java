package andrei.teplyh.repositories;

import andrei.teplyh.entities.accounts.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
    Administrator findAdministratorByLogin(String login);

    Administrator findAdministratorByAccountId(Long id);
}
