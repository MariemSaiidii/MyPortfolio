package mariem.portfolio.demo1.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mariem.portfolio.demo1.Entities.Course;
import mariem.portfolio.demo1.Services.ICourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@AllArgsConstructor
@Tag(name = "Gestion des cours")
public class CourseController {

    private final ICourseService courseService;

    @Operation(description = "Afficher tous les cours")
    @GetMapping
    public List<Course> getAll() {
        return courseService.getAll();
    }

    @Operation(description = "Afficher un cours par ID")
    @GetMapping("/{id}")
    public Course getById(@PathVariable Long id) {
        return courseService.getById(id);
    }

    @Operation(description = "Ajouter un cours")
    @PostMapping
    public Course add(@RequestBody Course c) {
        return courseService.save(c);
    }

    @Operation(description = "Modifier un cours")
    @PutMapping
    public Course update(@RequestBody Course c) {
        return courseService.save(c);
    }

    @Operation(description = "Supprimer un cours")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        courseService.delete(id);
    }
}

