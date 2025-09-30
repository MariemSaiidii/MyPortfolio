package mariem.portfolio.demo1.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mariem.portfolio.demo1.Entities.Project;
import mariem.portfolio.demo1.Services.IProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
@AllArgsConstructor
@Tag(name = "Gestion des projets")
public class ProjectController {

    private final IProjectService projectService;

    @Operation(description = "Afficher tous les projets")
    @GetMapping
    public List<Project> getAll() {
        return projectService.getAll();
    }

    @Operation(description = "Afficher un projet par ID")
    @GetMapping("/{id}")
    public Project getById(@PathVariable Long id) {
        return projectService.getById(id);
    }

    @Operation(description = "Ajouter un projet")
    @PostMapping
    public Project add(@RequestBody Project p) {
        return projectService.save(p);
    }

    @Operation(description = "Modifier un projet")
    @PutMapping
    public Project update(@RequestBody Project p) {
        return projectService.save(p);
    }

    @Operation(description = "Supprimer un projet")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }
}

