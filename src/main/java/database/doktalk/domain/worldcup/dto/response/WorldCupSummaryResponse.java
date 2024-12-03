package database.doktalk.domain.worldcup.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class WorldCupSummaryResponse {
    private Long worldCupId;
    private String worldCupName;
    private String coverImage;
}
