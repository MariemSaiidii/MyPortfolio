package mariem.portfolio.demo1.Services;

import mariem.portfolio.demo1.Entities.Formation;
import java.util.List;

public interface IFormationService {
    List<Formation> getAll();
    Formation getById(Long id);
    Formation save(Formation f);
    void delete(Long id);
}
