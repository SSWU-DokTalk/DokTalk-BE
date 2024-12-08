package database.doktalk.domain.discussionreview.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DiscussionReviewResponse {
    private Long Id;
    private String review;
    private String userName;
}
