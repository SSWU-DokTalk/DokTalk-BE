package database.doktalk.domain.discussionreview.repository;

import database.doktalk.domain.discussionreview.entity.DiscussionReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscussionReviewRepository extends JpaRepository<DiscussionReview, Long> {
}
