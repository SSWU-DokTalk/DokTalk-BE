package database.doktalk.domain.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DiaryListDTO {
    private Long diaryId;        // 글 ID
    private String title;   // 글 제목
    private String bookTitle;    // 책 제목
    private LocalDate createdAt; // 작성일
}