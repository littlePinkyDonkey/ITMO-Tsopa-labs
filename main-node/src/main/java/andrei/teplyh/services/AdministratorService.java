package andrei.teplyh.services;

import andrei.teplyh.entities.accounts.Administrator;

import java.util.List;

public interface AdministratorService {

    Administrator selectAdministratorWithMinimumWork();

    Administrator findAdministratorByLogin(String login);

    Administrator findAdministratorById(Long id);
}
