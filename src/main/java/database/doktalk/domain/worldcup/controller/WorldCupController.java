package database.doktalk.domain.worldcup.controller;

import database.doktalk.common.global.BaseResponse;
import database.doktalk.domain.worldcup.dto.request.WorldCupRequest;
import database.doktalk.domain.worldcup.dto.response.MatchIdResponse;
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

    @Operation(summary = "월드컵 경기 생성 API")
    @Parameters(value = {
            @Parameter(name = "round", description = "생성할 매치 라운드(몇강인지)"),
            @Parameter(name = "bookId1", description = "첫 번째 북 아이디" ),
            @Parameter(name = "bookId2", description = "두 번째 북 아이디"),
            @Parameter(name = "worldCupId", description = "속한 월드컵 아이디")
    })
    @PostMapping("/match")
    public BaseResponse<MatchIdResponse> createMatch(
            @RequestParam(name = "round")  int round,
            @RequestParam(name = "bookId1") Long bookId1,
            @RequestParam(name = "bookId1") Long bookId2,
            @RequestParam(name = "worldCupId") Long worldCupId){
        return BaseResponse.onSuccess(worldCupService.createMatch(round, bookId1, bookId2, worldCupId));
    }
}
