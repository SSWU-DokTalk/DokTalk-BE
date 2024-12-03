package database.doktalk.domain.discussionreview.service;

import database.doktalk.domain.discussionreview.dto.request.DiscussionReviewRequest;
import database.doktalk.domain.discussionreview.dto.response.DiscussionReviewIdResponse;

public interface DiscussionReviewService {
    DiscussionReviewIdResponse createDiscussionReview(DiscussionReviewRequest request);
}
