package mariem.portfolio.demo1.Services;

import lombok.AllArgsConstructor;
import mariem.portfolio.demo1.Entities.ContactInfo;
import mariem.portfolio.demo1.Repositories.ContactInfoRepo;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactInfoServiceImpl implements IContactInfoService{
    public final ContactInfoRepo contactInfoRepo;
    @Override
    public ContactInfo get() {
        return contactInfoRepo.findAll().stream().findFirst().orElse(null);
    }

    @Override
    public ContactInfo update(ContactInfo info) {
        return contactInfoRepo.save(info);
    }
}
