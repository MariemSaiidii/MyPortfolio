package mariem.portfolio.demo1.Services;

import mariem.portfolio.demo1.Entities.Skill;
import java.util.List;

public interface ISkillService {
    List<Skill> getAll();
    Skill getById(Long id);
    Skill save(Skill s);
    void delete(Long id);
}
