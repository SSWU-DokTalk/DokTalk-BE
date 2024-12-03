package database.doktalk.domain.discussion.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DiscussionDetailResponse {
    private Long discussionId;
    private String title;
    private String bookTitle;
    private String author;
    private String publisher;
    private String content;
}
