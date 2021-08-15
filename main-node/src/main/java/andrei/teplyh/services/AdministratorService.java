package andrei.teplyh.services;

import andrei.teplyh.entities.accounts.Administrator;

import java.util.List;

public interface AdministratorService {
    List<Administrator> getAllAdmins();

    Administrator selectAdministratorWithMinimumWork();

    Administrator findAdministratorByLogin(String login);

    Administrator findAdministratorById(Long id);
}
