package mariem.portfolio.demo1.Repositories;


import mariem.portfolio.demo1.Entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Long> {
}
