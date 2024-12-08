package database.doktalk.domain.diary.controller;

import database.doktalk.common.global.BaseResponse;
import database.doktalk.common.global.exception.CustomApiException;
import database.doktalk.common.global.exception.ErrorCode;
import database.doktalk.domain.book.entity.Book;
import database.doktalk.domain.diary.dto.DiaryBoardDTO;
import database.doktalk.domain.diary.dto.DiaryDetailDTO;
import database.doktalk.domain.diary.dto.DiaryListDTO;
import database.doktalk.domain.diary.dto.request.DiaryRequest;
import database.doktalk.domain.diary.dto.response.DiaryDetailResponse;
import database.doktalk.domain.diary.dto.response.DiaryIdResponse;
import database.doktalk.domain.diary.entity.Diary;
import database.doktalk.domain.diary.service.DiaryService;
import database.doktalk.domain.discussion.dto.request.DiscussionRequest;
import database.doktalk.domain.discussion.dto.response.DiscussionDetailResponse;
import database.doktalk.domain.discussion.dto.response.DiscussionIdResponse;
import database.doktalk.domain.user.entity.User;
import database.doktalk.domain.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diaries")
@CrossOrigin(origins = "http://localhost:8080")
public class DiaryController {

    private final DiaryService diaryService;
    private final UserRepository userRepository;

    // 검색 API
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String query) {
        List<Book> books = diaryService.searchBooks(query);
        return ResponseEntity.ok(books);
    }

    // 독서감상문 작성 API
    @PostMapping
    public BaseResponse<DiaryIdResponse> createDiscussion(
            @RequestBody DiaryRequest request) {
        return BaseResponse.onSuccess(diaryService.saveDiary(request));
    }

    //수정
    @PatchMapping("/{diaryId}")
    public ResponseEntity<Diary> updateDiary(
            @PathVariable Long diaryId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String bookTitle,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String bookCoverUrl,
            @RequestParam(required = false) String content) {

        Diary updatedDiary = diaryService.updateDiary(diaryId, title, bookTitle, author, publisher,bookCoverUrl, content);
        return ResponseEntity.ok(updatedDiary);
    }

    @DeleteMapping("/{diaryId}")
    public ResponseEntity<String> deleteDiary(@PathVariable Long diaryId) {
        diaryService.deleteDiary(diaryId);
        return ResponseEntity.ok("Diary deleted successfully");
    }

    // 사용자 독서감상문 목록 API
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<DiaryListDTO>> getUserDiaries(@PathVariable Long userId) {
        List<DiaryListDTO> diaries = diaryService.getUserDiaryList(userId);
        if (diaries == null || diaries.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(diaries);
    }

    // 글 상세조회
    @GetMapping("/{diaryId}")
    public BaseResponse<DiaryDetailResponse> getDiscussion(@PathVariable Long diaryId) {
        return BaseResponse.onSuccess(diaryService.getDiaryDetail(diaryId));
    }


    @PatchMapping("/{diaryId}/like")
    public ResponseEntity<Diary> likeDiary(@PathVariable Long diaryId) {
        Diary updatedDiary = diaryService.likeDiary(diaryId);
        return ResponseEntity.ok(updatedDiary); // 업데이트된 diary 반환
    }

    @Operation(summary = "게시판 리스트 조회 api")
    @GetMapping("/board")
    public BaseResponse<List<DiaryBoardDTO>> getDiaryBoard() {
        return BaseResponse.onSuccess(diaryService.getBoardDiaries());
    }
}
