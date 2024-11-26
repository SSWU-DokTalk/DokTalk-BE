package database.doktalk.domain.diary.controller;

import database.doktalk.domain.diary.dto.DiaryDetailDTO;
import database.doktalk.domain.diary.dto.DiaryListDTO;
import database.doktalk.domain.diary.entity.Diary;
import database.doktalk.domain.diary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    // 독서감상문 작성 API
    @PostMapping
    public ResponseEntity<Diary> createDiary(
            @RequestParam Long userId,
            @RequestParam String title,
            @RequestParam String bookTitle,
            @RequestParam String author,
            @RequestParam String publisher,
            @RequestParam String bookCoverUrl,
            @RequestParam String content) {

        Diary diary = Diary.builder()
                .userId(userId)
                .title(title)
                .bookTitle(bookTitle)
                .author(author)
                .publisher(publisher)
                .bookCoverUrl(bookCoverUrl)
                .content(content)
                .build();
        Diary savedDiary = diaryService.saveDiary(diary);
        return ResponseEntity.ok(savedDiary);
    }

    @PatchMapping("/{diaryId}")
    public ResponseEntity<Diary> updateDiary(
            @PathVariable Long diaryId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String bookTitle,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String bookCoverUrl,
            @RequestParam(required = false) String content) {

        Diary updatedDiary = diaryService.updateDiary(diaryId, title, bookTitle, author, publisher, bookCoverUrl, content);
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
        return ResponseEntity.ok(diaries);
    }

    // 글 상세조회
    @GetMapping("/{diaryId}")
    public ResponseEntity<DiaryDetailDTO> getDiary(@PathVariable Long diaryId) {
        DiaryDetailDTO diary = diaryService.getDiaryDetail(diaryId);
        return ResponseEntity.ok(diary);
    }


}

