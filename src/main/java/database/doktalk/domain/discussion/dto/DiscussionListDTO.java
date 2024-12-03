package database.doktalk.domain.discussion.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DiscussionListDTO {

    private Long discussionId; // 토론글 ID
    private String title; // 토론글 제목
    private String bookTitle; // 책 제목
    private String user; // 작성자 이름

}
