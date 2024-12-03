package database.doktalk.domain.discussion.service;

import database.doktalk.domain.book.entity.Book;
import database.doktalk.domain.book.repository.BookRepository;
import database.doktalk.domain.discussion.dto.DiscussionListDTO;
import database.doktalk.domain.discussion.entity.Discussion;
import database.doktalk.domain.discussion.entity.Vote;
import database.doktalk.domain.discussion.repository.DiscussionRepository;
import database.doktalk.domain.discussion.repository.VoteRepository;
import database.doktalk.domain.user.entity.User;
import database.doktalk.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// DiscussionService.java

@Service
@RequiredArgsConstructor
public class DiscussionService {

    private final BookRepository bookRepository;
    private final DiscussionRepository discussionRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;



    // 책 검색
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findBooksByKeyword(keyword);
    }

    // 토론글 저장
    public Discussion saveDiscussion(Discussion discussion) {
        return discussionRepository.save(discussion);
    }

    // 토론글 수정
    public Discussion updateDiscussion(Long discussionId, String title, String bookTitle, String author, String publisher, String content) {
        Discussion discussion = discussionRepository.findById(discussionId)
                .orElseThrow(() -> new IllegalArgumentException("Discussion not found with id: " + discussionId));

        if (title != null) discussion.setTitle(title);
        if (bookTitle != null) discussion.setBookTitle(bookTitle);
        if (author != null) discussion.setAuthor(author);
        if (publisher != null) discussion.setPublisher(publisher);
        if (content != null) discussion.setContent(content);

        return discussionRepository.save(discussion);
    }

    // 토론글 삭제
    public void deleteDiscussion(Long discussionId) {
        Discussion discussion = discussionRepository.findById(discussionId)
                .orElseThrow(() -> new IllegalArgumentException("Discussion not found with id: " + discussionId));

        discussionRepository.delete(discussion);
    }

    // 사용자별 토론글 목록 조회
    public List<DiscussionListDTO> getAllDiscussions() {
        List<Discussion> discussions = discussionRepository.findAll();

        return discussions.stream()
                .map(discussion -> {
                    // 작성자 이름 가져오기
                    User user = userRepository.findById(discussion.getUserId()).orElse(null);
                    String authorName = (user != null) ? user.getName() : "Unknown";  // 작성자 이름이 없을 경우 처리

                    return new DiscussionListDTO(
                            discussion.getId(),
                            discussion.getTitle(),
                            discussion.getBookTitle(),
                            authorName
                    );
                })
                .collect(Collectors.toList());
    }

    // 토론글 상세 조회
    public Discussion getDiscussionDetail(Long discussionId) {
        return discussionRepository.findById(discussionId)
                .orElseThrow(() -> new IllegalArgumentException("Discussion not found with id: " + discussionId));
    }

    // 찬성 투표
    public Discussion voteApproval(Long discussionId, Long userId) {
        Discussion discussion = discussionRepository.findById(discussionId)
                .orElseThrow(() -> new IllegalArgumentException("Discussion not found with id: " + discussionId));

        // User 조회
        User user = userRepository.findById(userId)  // userId를 Long으로 변경
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 중복 투표 방지
        if (voteRepository.existsByDiscussionIdAndUserIdAndVoted(discussionId, user.getId(), true)) {
            throw new IllegalStateException("User has already voted for approval.");
        }

        // 투표 기록 저장
        Vote vote = Vote.builder()
                .discussion(discussion)
                .user(user)
                .voted(true)
                .build();
        voteRepository.save(vote);

        // 찬성 수 증가
        discussion.setApproval(discussion.getApproval() + 1);
        return discussionRepository.save(discussion);
    }

    // 반대 투표
    public Discussion voteOpposition(Long discussionId, Long userId) {  // userId를 Long으로 변경
        Discussion discussion = discussionRepository.findById(discussionId)
                .orElseThrow(() -> new IllegalArgumentException("Discussion not found with id: " + discussionId));

        // User 조회
        User user = userRepository.findById(userId)  // userId를 Long으로 변경
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        // 중복 투표 방지
        if (voteRepository.existsByDiscussionIdAndUserIdAndVoted(discussionId, user.getId(), false)) {
            throw new IllegalStateException("User has already voted for opposition.");
        }

        // 투표 기록 저장
        Vote vote = Vote.builder()
                .discussion(discussion)
                .user(user)
                .voted(false)
                .build();
        voteRepository.save(vote);

        // 반대 수 증가
        discussion.setOpposite(discussion.getOpposite() + 1);
        return discussionRepository.save(discussion);
    }

    // 찬성/반대 총 투표 수 확인
    public String getVoteCounts(Long discussionId) {
        Discussion discussion = discussionRepository.findById(discussionId)
                .orElseThrow(() -> new IllegalArgumentException("Discussion not found with id: " + discussionId));

        int totalVotes = discussion.getApproval() + discussion.getOpposite();
        if (totalVotes == 0) {
            return "No votes yet.";
        }

        double approvalPercent = (double) discussion.getApproval() / totalVotes * 100;
        double oppositionPercent = (double) discussion.getOpposite() / totalVotes * 100;

        return String.format("찬성: %d (%.2f%%), 반대: %d (%.2f%%) ",
                discussion.getApproval(), approvalPercent, discussion.getOpposite(), oppositionPercent);
    }
}
