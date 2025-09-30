package mariem.portfolio.demo1.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mariem.portfolio.demo1.Entities.ContactInfo;
import mariem.portfolio.demo1.Services.IContactInfoService;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/contact")
@Tag(name = "Gestion des informations de contact")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactInfoController {

    private final IContactInfoService contactService;

    @Operation(description = "Afficher les informations de contact")
    @GetMapping
    public ContactInfo getContact() {
        return contactService.get();
    }

    @Operation(description = "Mettre à jour les informations de contact")
    @PutMapping
    public ContactInfo updateContact(@RequestBody ContactInfo info) {
        return contactService.update(info);
    }
}
