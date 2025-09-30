package mariem.portfolio.demo1.Services;

import lombok.AllArgsConstructor;
import mariem.portfolio.demo1.Entities.Project;
import mariem.portfolio.demo1.Repositories.ProjectRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements IProjectService {

    private final ProjectRepo projectRepo;

    @Override
    public List<Project> getAll() {
        return projectRepo.findAll();
    }

    @Override
    public Project getById(Long id) {
        return projectRepo.findById(id).orElse(null);
    }

    @Override
    public Project save(Project p) {
        return projectRepo.save(p);
    }

    @Override
    public void delete(Long id) {
        projectRepo.deleteById(id);
    }
}
