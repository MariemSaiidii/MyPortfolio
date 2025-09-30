package mariem.portfolio.demo1.Services;

import mariem.portfolio.demo1.Entities.Course;
import java.util.List;

public interface ICourseService {
    List<Course> getAll();
    Course getById(Long id);
    Course save(Course c);
    void delete(Long id);
}
