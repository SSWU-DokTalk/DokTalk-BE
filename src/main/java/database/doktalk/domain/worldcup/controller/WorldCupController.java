package database.doktalk.domain.worldcup.controller;

import database.doktalk.common.global.BaseResponse;
import database.doktalk.domain.worldcup.dto.request.NextRoundRequest;
import database.doktalk.domain.worldcup.dto.request.WorldCupRequest;
import database.doktalk.domain.worldcup.dto.response.MatchIdResponse;
import database.doktalk.domain.worldcup.dto.response.RoundResponse;
import database.doktalk.domain.worldcup.dto.response.WorldCupIdResponse;
import database.doktalk.domain.worldcup.entity.WorldCup;
import database.doktalk.domain.worldcup.service.WorldCupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "월드컵 API", description = "월드컵 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/world-cup")
public class WorldCupController {

    private final WorldCupService worldCupService;

    @Operation(summary = "월드컵 생성 API")
    @PostMapping
    public BaseResponse<WorldCupIdResponse> createWorldCup(@RequestBody @Valid WorldCupRequest request){
        return BaseResponse.onSuccess(worldCupService.createWorldCup(request));
    }

    @Operation(summary = "월드컵 다음 라운드 생성 API")
    @PostMapping("/next-round")
    public BaseResponse<RoundResponse> createMatch(@RequestBody @Valid NextRoundRequest request){
        return BaseResponse.onSuccess(worldCupService.createNextRound(request));
    }
}
