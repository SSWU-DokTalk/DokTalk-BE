package database.doktalk.domain.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiaryDetailDTO {
    private String title;   // 글 제목
    private String bookTitle;    // 책 제목
    private String author;       // 지은이
    private String publisher;    // 출판사
    private String bookCoverUrl; // 책 표지 URL
    private String content;      // 글 내용
    private Integer likeCount;       // 좋아요 수





}
