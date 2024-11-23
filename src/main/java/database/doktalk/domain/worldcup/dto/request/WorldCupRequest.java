package database.doktalk.domain.worldcup.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.List;

@Getter
public class WorldCupRequest {
    private String subject;

    @Size(min = 16, max = 16, message = "bookIds는 반드시 16개의 ID를 포함해야 합니다.")
    @Schema(description = "16개의 책 ID 리스트", example = "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16]")
    private List<Long> bookIds;
}
