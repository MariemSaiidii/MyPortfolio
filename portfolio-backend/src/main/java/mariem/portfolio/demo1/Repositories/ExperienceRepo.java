package mariem.portfolio.demo1.Repositories;

import mariem.portfolio.demo1.Entities.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienceRepo extends JpaRepository<Experience, Long> {
}
