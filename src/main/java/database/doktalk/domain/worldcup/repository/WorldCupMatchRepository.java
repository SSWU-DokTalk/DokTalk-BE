package database.doktalk.domain.worldcup.repository;

import database.doktalk.domain.worldcup.entity.WorldCupMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorldCupMatchRepository extends JpaRepository<WorldCupMatch, Long> {
}
