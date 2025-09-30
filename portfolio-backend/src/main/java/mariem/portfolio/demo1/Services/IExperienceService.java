package mariem.portfolio.demo1.Services;

import mariem.portfolio.demo1.Entities.Experience;
import java.util.List;

public interface IExperienceService {
    List<Experience> getAll();
    Experience getById(Long id);
    Experience save(Experience e);
    void delete(Long id);
}
