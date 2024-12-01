package database.doktalk.domain.discussion.controller;

import database.doktalk.domain.book.dto.DiscussionBookDTO;
import database.doktalk.domain.book.entity.Book;
import database.doktalk.domain.diary.dto.DiaryDetailDTO;
import database.doktalk.domain.diary.dto.DiaryListDTO;
import database.doktalk.domain.diary.entity.Diary;
import database.doktalk.domain.discussion.entity.Discussion;
import database.doktalk.domain.discussion.service.DiscussionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class DiscussionController {
    private final DiscussionService discussionService;

    // 검색 API
    @GetMapping("/search")
    public ResponseEntity<List<DiscussionBook>> searchBooks(@RequestParam String query) {
        List<DiscussionBookDTO> books = discussionService.searchBooks(query);
        return ResponseEntity.ok(books);
    }

    // 독서감상문 작성 API
    @PostMapping
    public ResponseEntity<Discussion> createDiscussion(
            @RequestParam Long userId,
            @RequestParam String title,
            @RequestParam String bookTitle,
            @RequestParam String author,
            @RequestParam String publisher,
            @RequestParam String content) {

        Discussion discussion = Discussion.builder()
                .userId(userId)
                .title(title)
                .bookTitle(bookTitle)
                .author(author)
                .publisher(publisher)
                .content(content)
                .build();
        Diary savedDiscussion = discussionService.saveDiscussion(discussion);
        return ResponseEntity.ok(savedDiscussion);
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

        Diary updatedDiscussion = discussionService.updateDiscussion(discussionId, title, bookTitle, author, publisher, content);
        return ResponseEntity.ok(updatedDiscussion);
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
