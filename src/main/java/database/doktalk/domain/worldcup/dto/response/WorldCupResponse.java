package database.doktalk.domain.worldcup.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@AllArgsConstructor
@Builder
public class WorldCupResponse {
    private String title;
    private List<BookSummaryResponse> books;
}
