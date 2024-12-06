package database.doktalk.domain.diary.repository;

import database.doktalk.domain.diary.dto.DiaryListDTO;
import database.doktalk.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {


    List<Diary> findByUserId(Long userId);


    @Query("SELECT d FROM Diary d WHERE d.user.id = :userId")
    List<Diary> findDiaryListByUserId(@Param("userId") Long userId);

    @Query("SELECT d FROM Diary d WHERE LOWER(d.bookTitle) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(d.author) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Diary> findByKeyword(@Param("keyword") String keyword);
}
