package database.doktalk.domain.discussionreview.mapper;

import database.doktalk.domain.discussion.entity.Discussion;
import database.doktalk.domain.discussionreview.dto.request.DiscussionReviewRequest;
import database.doktalk.domain.discussionreview.entity.DiscussionReview;
import database.doktalk.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class DiscussionReviewMapper {

    public DiscussionReview toDiscussionReview(DiscussionReviewRequest request, User user, Discussion discussion) {
        return DiscussionReview.builder()
                .review(request.getReview())
                .user(user)
                .discussion(discussion)
                .build();
    }
}
