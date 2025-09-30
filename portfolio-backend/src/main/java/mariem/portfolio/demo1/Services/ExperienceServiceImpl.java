package mariem.portfolio.demo1.Services;

import lombok.AllArgsConstructor;
import mariem.portfolio.demo1.Entities.Experience;
import mariem.portfolio.demo1.Repositories.ExperienceRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ExperienceServiceImpl implements IExperienceService {

    private final ExperienceRepo experienceRepo;

    @Override
    public List<Experience> getAll() {
        return experienceRepo.findAll();
    }

    @Override
    public Experience getById(Long id) {
        return experienceRepo.findById(id).orElse(null);
    }

    @Override
    public Experience save(Experience e) {
        return experienceRepo.save(e);
    }

    @Override
    public void delete(Long id) {
        experienceRepo.deleteById(id);
    }
}
