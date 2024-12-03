package database.doktalk.domain.discussionreview.service;

import database.doktalk.domain.discussionreview.dto.request.DiscussionReviewRequest;
import database.doktalk.domain.discussionreview.dto.response.DiscussionReviewIdResponse;
import database.doktalk.domain.discussionreview.dto.response.DiscussionReviewResponse;

import java.util.List;

public interface DiscussionReviewService {
    DiscussionReviewIdResponse createDiscussionReview(DiscussionReviewRequest request);
    List<DiscussionReviewResponse> getDiscussionReviews(Long discussionId);
    //DiscussionReviewIdResponse deleteDiscussionReview(Long discussionReviewId);
}
