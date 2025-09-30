package mariem.portfolio.demo1.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mariem.portfolio.demo1.Entities.Skill;
import mariem.portfolio.demo1.Services.ISkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/skill")
@AllArgsConstructor
@Tag(name = "Gestion des compétences")
public class SkillController {

    private final ISkillService skillService;

    @Operation(description = "Afficher toutes les compétences")
    @GetMapping
    public List<Skill> getAll() {
        return skillService.getAll();
    }

    @Operation(description = "Afficher une compétence par ID")
    @GetMapping("/{id}")
    public Skill getById(@PathVariable Long id) {
        return skillService.getById(id);
    }

    @Operation(description = "Ajouter une compétence")
    @PostMapping
    public Skill add(@RequestBody Skill s) {
        return skillService.save(s);
    }

    @Operation(description = "Modifier une compétence")
    @PutMapping
    public Skill update(@RequestBody Skill s) {
        return skillService.save(s);
    }

    @Operation(description = "Supprimer une compétence")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        skillService.delete(id);
    }
}

