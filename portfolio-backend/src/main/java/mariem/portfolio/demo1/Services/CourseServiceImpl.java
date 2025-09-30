package mariem.portfolio.demo1.Services;

import lombok.AllArgsConstructor;
import mariem.portfolio.demo1.Entities.Course;
import mariem.portfolio.demo1.Repositories.CourseRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final CourseRepo courseRepo;

    @Override
    public List<Course> getAll() {
        return courseRepo.findAll();
    }

    @Override
    public Course getById(Long id) {
        return courseRepo.findById(id).orElse(null);
    }

    @Override
    public Course save(Course c) {
        return courseRepo.save(c);
    }

    @Override
    public void delete(Long id) {
        courseRepo.deleteById(id);
    }
}
