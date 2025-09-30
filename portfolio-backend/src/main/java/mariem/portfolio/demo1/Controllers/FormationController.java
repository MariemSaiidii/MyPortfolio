package mariem.portfolio.demo1.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mariem.portfolio.demo1.Entities.Formation;
import mariem.portfolio.demo1.Services.IFormationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/formation")
@Tag(name = "Gestion des formations")
public class FormationController {

    private final IFormationService formationService;

    @Operation(description = "Afficher toutes les formations")
    @GetMapping
    public List<Formation> getAll() {
        return formationService.getAll();
    }

    @Operation(description = "Afficher une formation par ID")
    @GetMapping("/{id}")
    public Formation getById(@PathVariable Long id) {
        return formationService.getById(id);
    }

    @Operation(description = "Ajouter une formation")
    @PostMapping
    public Formation add(@RequestBody Formation f) {
        return formationService.save(f);
    }

    @Operation(description = "Modifier une formation")
    @PutMapping
    public Formation update(@RequestBody Formation f) {
        return formationService.save(f);
    }

    @Operation(description = "Supprimer une formation")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        formationService.delete(id);
    }
}
