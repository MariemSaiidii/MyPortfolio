package mariem.portfolio.demo1.Services;

import lombok.AllArgsConstructor;
import mariem.portfolio.demo1.Entities.Formation;
import mariem.portfolio.demo1.Repositories.FormationRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class FormationServiceImpl implements IFormationService {

    private final FormationRepo formationRepo;

    @Override
    public List<Formation> getAll() {
        return formationRepo.findAll();
    }

    @Override
    public Formation getById(Long id) {
        return formationRepo.findById(id).orElse(null);
    }

    @Override
    public Formation save(Formation f) {
        return formationRepo.save(f);
    }

    @Override
    public void delete(Long id) {
        formationRepo.deleteById(id);
    }
}
