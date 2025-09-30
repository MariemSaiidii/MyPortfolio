package mariem.portfolio.demo1.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mariem.portfolio.demo1.Entities.Experience;
import mariem.portfolio.demo1.Services.IExperienceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experience")
@AllArgsConstructor
@Tag(name = "Gestion des expériences")
public class ExperienceController {

    private final IExperienceService experienceService;

    @Operation(description = "Afficher toutes les expériences")
    @GetMapping
    public List<Experience> getAll() {
        return experienceService.getAll();
    }

    @Operation(description = "Afficher une expérience par ID")
    @GetMapping("/{id}")
    public Experience getById(@PathVariable Long id) {
        return experienceService.getById(id);
    }

    @Operation(description = "Ajouter une expérience")
    @PostMapping
    public Experience add(@RequestBody Experience e) {
        return experienceService.save(e);
    }

    @Operation(description = "Modifier une expérience")
    @PutMapping
    public Experience update(@RequestBody Experience e) {
        return experienceService.save(e);
    }

    @Operation(description = "Supprimer une expérience")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        experienceService.delete(id);
    }
}

