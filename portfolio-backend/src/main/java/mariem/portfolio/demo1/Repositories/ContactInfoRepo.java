package mariem.portfolio.demo1.Repositories;

import mariem.portfolio.demo1.Entities.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInfoRepo extends JpaRepository<ContactInfo, Long> {
}
