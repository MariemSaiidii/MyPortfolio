package mariem.portfolio.demo1.Services;

import mariem.portfolio.demo1.Entities.Project;
import java.util.List;

public interface IProjectService {
    List<Project> getAll();
    Project getById(Long id);
    Project save(Project p);
    void delete(Long id);
}
