package mariem.portfolio.demo1.Repositories;

import mariem.portfolio.demo1.Entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormationRepo extends JpaRepository<Formation, Long> {
}
