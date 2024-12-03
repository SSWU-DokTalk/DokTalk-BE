package database.doktalk.domain.discussion.repository;

import database.doktalk.domain.discussion.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    // 특정 사용자가 특정 토론에 대해 찬성 또는 반대 투표를 했는지 확인
    @Query("SELECT COUNT(v) > 0 FROM Vote v WHERE v.discussion.id = :discussionId AND v.user.id = :userId AND v.voted = :voted")
    boolean existsByDiscussionIdAndUserIdAndVoted(@Param("discussionId") Long discussionId,
                                                  @Param("userId") Long userId,
                                                  @Param("voted") boolean voted);

    // 찬성 투표 수 조회
    @Query("SELECT COUNT(v) FROM Vote v WHERE v.discussion.id = :discussionId AND v.voted = true")
    int countApprovalVotes(@Param("discussionId") Long discussionId);

    // 반대 투표 수 조회
    @Query("SELECT COUNT(v) FROM Vote v WHERE v.discussion.id = :discussionId AND v.voted = false")
    int countOppositionVotes(@Param("discussionId") Long discussionId);
}
