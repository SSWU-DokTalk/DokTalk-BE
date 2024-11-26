package database.doktalk.domain.worldcup.dto.request;

import database.doktalk.domain.book.entity.Book;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;

@Getter
public class NextRoundRequest {

    @Schema(description = "속한 월드컵 아이디", example = "1")
    private Long worldCupId;
    @Schema(description = "생성할 매치 라운드(몇 강인지)", example = "8")
    private int round;
    @Schema(description = "들어갈 책 아이디", example = "{1,2,3,4,5,6,7,8}")
    private List<Long> bookIds;
}
