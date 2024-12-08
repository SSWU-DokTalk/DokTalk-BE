package database.doktalk.domain.diary.dto.request;

import lombok.Getter;

@Getter
public class DiaryRequest {
    private String title;
    private String bookTitle;
    private String author;
    private String publisher;
    private String bookCoverUrl;
    private String content;
    private Long userId;
}
