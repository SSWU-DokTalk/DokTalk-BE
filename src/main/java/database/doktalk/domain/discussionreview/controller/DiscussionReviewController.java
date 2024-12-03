package database.doktalk.domain.discussionreview.controller;


import database.doktalk.common.global.BaseResponse;
import database.doktalk.domain.discussionreview.dto.request.DiscussionReviewRequest;
import database.doktalk.domain.discussionreview.dto.response.DiscussionReviewIdResponse;
import database.doktalk.domain.discussionreview.service.DiscussionReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "토론방 댓글 API", description = "토론방 댓글 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/world-cup")
public class DiscussionReviewController {

    private final DiscussionReviewService discussionReviewService;

    @Operation(summary = "토론방 댓글 생성 생성 API")
    @PostMapping
    public BaseResponse<DiscussionReviewIdResponse> createEvent (@RequestBody DiscussionReviewRequest request){
            return BaseResponse.onSuccess(discussionReviewService.createDiscussionReview(request));
    }
}
