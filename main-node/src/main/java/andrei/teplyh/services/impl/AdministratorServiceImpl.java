package andrei.teplyh.services.impl;

import andrei.teplyh.entities.accounts.Administrator;
import andrei.teplyh.repositories.AdministratorRepository;
import andrei.teplyh.services.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorServiceImpl(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @Override
    public List<Administrator> getAllAdmins() {
        return administratorRepository.findAll();
    }

    @Override
    public Administrator selectAdministratorWithMinimumWork() {
        List<Administrator> administrators = administratorRepository.findAll();
        Administrator selectedAdministrator = null;

        int inspections = Integer.MAX_VALUE;
        for (Administrator administrator : administrators) {
            if (administrator.getInspections().size() < inspections) {
                inspections = administrator.getInspections().size();
                selectedAdministrator = administrator;
            }
        }

        return selectedAdministrator;
    }

    @Override
    public Administrator findAdministratorByLogin(String login) {
        return administratorRepository.findAdministratorByLogin(login);
    }

    @Override
    public Administrator findAdministratorById(Long id) {
        return administratorRepository.findAdministratorByAccountId(id);
    }
}
