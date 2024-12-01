package database.doktalk.domain.discussion.repository;

import database.doktalk.domain.diary.dto.DiaryListDTO;
import database.doktalk.domain.diary.entity.Diary;
import database.doktalk.domain.discussion.entity.Discussion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public class DiscussionRepository {
    List<Discussion> findByUserId(Long userId);

    @Query("SELECT d " +
            "FROM Discussion d WHERE d.userId = :userId")
    List<DiscussionDTO> findDDiscussionByUserId(@Param("userId") Long userId);

}
