package database.doktalk.domain.discussionreview.dto.request;


import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiscussionReviewRequest {
    private String review;
    private Long userId;
    private Long DiscussionId;
}
