package database.doktalk.domain.worldcup.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class BookSummaryResponse {
    private String title;
    //private String url;// 이미지 url
}
