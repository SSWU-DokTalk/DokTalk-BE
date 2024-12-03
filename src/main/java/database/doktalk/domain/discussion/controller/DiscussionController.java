package database.doktalk.domain.discussion.controller;

import database.doktalk.domain.book.entity.Book;
import database.doktalk.domain.discussion.dto.DiscussionListDTO;
import database.doktalk.domain.discussion.entity.Discussion;
import database.doktalk.domain.discussion.service.DiscussionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discussions")
@CrossOrigin(origins="http://localhost:8080")
public class DiscussionController {

    private final DiscussionService discussionService;

    public DiscussionController(DiscussionService discussionService) {
        this.discussionService = discussionService;
    }

    // 책 검색 API
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String query) {
        List<Book> books = discussionService.searchBooks(query);
        return ResponseEntity.ok(books);
    }

    // 토론글 작성 API
    @PostMapping
    public ResponseEntity<Discussion> createDiscussion(
            @RequestParam Long userId,
            @RequestParam String title,
            @RequestParam String bookTitle,
            @RequestParam String author,
            @RequestParam String publisher,
            @RequestParam String content) {

        Discussion discussion = Discussion.builder()
                .title(title)
                .bookTitle(bookTitle)
                .author(author)
                .publisher(publisher)
                .content(content)
                .build();
        Discussion savedDiscussion = discussionService.saveDiscussion(discussion);
        return ResponseEntity.ok(savedDiscussion);
    }

    // 토론글 수정 API
    @PatchMapping("/{discussionId}")
    public ResponseEntity<Discussion> updateDiscussion(
            @PathVariable Long discussionId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String bookTitle,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String content) {

        Discussion updatedDiscussion = discussionService.updateDiscussion(
                discussionId, title, bookTitle, author, publisher, content);
        return ResponseEntity.ok(updatedDiscussion);
    }

    // 토론글 삭제 API
    @DeleteMapping("/{discussionId}")
    public ResponseEntity<String> deleteDiscussion(@PathVariable Long discussionId) {
        discussionService.deleteDiscussion(discussionId);
        return ResponseEntity.ok("Discussion deleted successfully");
    }

    // 토론글 목록 조회 API
    @GetMapping
    public ResponseEntity<List<DiscussionListDTO>> getAllDiscussions() {
        List<DiscussionListDTO> discussions = discussionService.getAllDiscussions();
        return ResponseEntity.ok(discussions); // 모든 토론글 정보 반환
    }

    // 토론글 상세조회 API
    @GetMapping("/{discussionId}")
    public ResponseEntity<Discussion> getDiscussion(@PathVariable Long discussionId) {
        Discussion discussion = discussionService.getDiscussionDetail(discussionId);
        return ResponseEntity.ok(discussion);
    }

    // 찬성 투표 API
    @PostMapping("/{discussionId}/vote/approval")
    public ResponseEntity<Discussion> voteApproval(@PathVariable Long discussionId, @RequestParam Long userId) { // userId를 Long으로 변경
        Discussion discussion = discussionService.voteApproval(discussionId, userId);
        return ResponseEntity.ok(discussion);
    }

    // 반대 투표 API
    @PostMapping("/{discussionId}/vote/opposition")
    public ResponseEntity<Discussion> voteOpposition(@PathVariable Long discussionId, @RequestParam Long userId) { // userId를 Long으로 변경
        Discussion discussion = discussionService.voteOpposition(discussionId, userId);
        return ResponseEntity.ok(discussion);
    }

    // 투표 결과 조회 API
    @GetMapping("/{discussionId}/votes")
    public ResponseEntity<String> getVoteCounts(@PathVariable Long discussionId) {
        String voteCounts = discussionService.getVoteCounts(discussionId);
        return ResponseEntity.ok(voteCounts);
    }
}
