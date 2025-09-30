package mariem.portfolio.demo1.Services;

import lombok.AllArgsConstructor;
import mariem.portfolio.demo1.Entities.Skill;
import mariem.portfolio.demo1.Repositories.SkillRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class SkillServiceImpl implements ISkillService {

    private final SkillRepo skillRepo;

    @Override
    public List<Skill> getAll() {
        return skillRepo.findAll();
    }

    @Override
    public Skill getById(Long id) {
        return skillRepo.findById(id).orElse(null);
    }

    @Override
    public Skill save(Skill s) {
        return skillRepo.save(s);
    }

    @Override
    public void delete(Long id) {
        skillRepo.deleteById(id);
    }
}
