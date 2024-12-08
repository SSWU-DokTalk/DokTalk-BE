package database.doktalk.domain.diary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiaryBoardDTO {

    private String title;      // 감상문 제목
    private String bookTitle;  // 책 제목
    private String userId;   // 작성자 이름
    private Long diaryId;
}
