package database.doktalk.domain.discussion.dto.request;

import lombok.Getter;

@Getter
public class DiscussionRequest {
    private String title;
    private String bookTitle;
    private String author;
    private String content;
    private String publisher;
    private Long userId;
}
