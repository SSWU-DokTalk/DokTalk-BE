package database.doktalk.domain.discussion.repository;

import database.doktalk.domain.book.dto.DiscussionBookDTO;
import database.doktalk.domain.discussion.entity.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

// DiscussionRepository.java

@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {

    // 사용자 ID (Long 타입으로 User 엔티티의 id를 기준으로)로 토론글 검색
    List<Discussion> findByUser_Id(Long userId);  // 수정된 부분

    List<Discussion> findAll();
    // 사용자 ID로 특정 정보(책 관련)만 조회
    @Query("SELECT d FROM Discussion d WHERE d.user.id = :userId")
    List<Discussion> findDiscussionBookByUserId(@Param("userId") Long userId);  // 수정된 부분
}

