package database.doktalk.domain.worldcup.controller;

import database.doktalk.common.global.BaseResponse;
import database.doktalk.domain.worldcup.dto.response.WorldCupResponse;
import database.doktalk.domain.worldcup.dto.response.WorldCupSummaryResponse;
import database.doktalk.domain.worldcup.service.WorldCupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "월드컵 API", description = "월드컵 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/world-cup")
public class WorldCupController {

    private final WorldCupService worldCupService;


    @Operation(summary = "전체 월드컵 조회 API")
    @GetMapping
    public BaseResponse<List<WorldCupSummaryResponse>> getWorldCups(){
        return BaseResponse.onSuccess(worldCupService.getWorldCups());
    }

    @Operation(summary = "월드컵 상세 조회 API")
    @GetMapping("/{worldCupId}")
    public BaseResponse<WorldCupResponse> getWorldCupDetail(
            @Parameter(description = "조회할 월드컵 Id") @PathVariable("worldCupId") Long worldCupId
    ){
        return BaseResponse.onSuccess(worldCupService.getWorldCupDetail(worldCupId));
    }


}
