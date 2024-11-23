package database.doktalk.domain.worldcup.repository;


import database.doktalk.domain.worldcup.entity.WorldCup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorldCupRepository extends JpaRepository<WorldCup, Long> {
}
