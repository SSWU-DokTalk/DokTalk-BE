package database.doktalk.domain.worldcup.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class WorldCupSummaryResponse {
    private Long worldCupId;
    private String worldCupName;
    private String coverImage;
}
