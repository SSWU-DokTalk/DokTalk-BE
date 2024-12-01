package database.doktalk.domain.discussion.service;


import database.doktalk.domain.book.entity.Book;
import database.doktalk.domain.book.repository.BookRepository;
import database.doktalk.domain.diary.dto.DiaryDetailDTO;
import database.doktalk.domain.diary.dto.DiaryListDTO;
import database.doktalk.domain.diary.entity.Diary;
import database.doktalk.domain.discussion.entity.Discussion;
import database.doktalk.domain.discussion.repository.DiscussionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DiscussionService {


    private final BookRepository bookRepository;
    private final DiscussionRepository discussionRepository;



    //책 검색
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findBooksByKeyword(keyword);
    }

    // 토론글 저장
    public Discussion saveDiscussion(Discussion discussion) {
        return discussionRepository.save(discussion);
    }


    public Discussion updateDiscussion(Long diaryId, String title, String bookTitle, String author, String publisher, String bookCoverUrl, String content) {
        Discussion discussion = discussionRepository.findById(discussionId)
                .orElseThrow(() -> new IllegalArgumentException("Discussion not found with id: " + discussionId));

        if (title != null) discussion.setTitle(title);
        if (bookTitle != null) discussion.setBookTitle(bookTitle);
        if (author != null) discussion.setAuthor(author);
        if (publisher != null) discussion.setPublisher(publisher);
        if (content != null) discussion.setContent(content);

        return discussionRepository.save(discussion);
    }

    public void deleteDiary(Long diaryId) {
        Diary diary = discussionRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("Diary not found with id: " + diaryId));

        discussionRepository.delete(diary);
    }



    // 사용자별 독서감상문 목록 조회
    public List<DiaryListDTO> getUserDiaryList(Long userId) {
        return discussionRepository.findDiaryListByUserId(userId);
    }

    // 글 상세조회
    public DiaryDetailDTO getDiaryDetail(Long diaryId) {
        Diary diary = discussionRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("해당 글을 찾을 수 없습니다."));

        String formattedCreatedAt = diary.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        return new DiaryDetailDTO(
                diary.getTitle(),
                diary.getBookTitle(),
                diary.getAuthor(),
                diary.getPublisher(),
                diary.getBookCoverUrl(),
                diary.getContent(),
                formattedCreatedAt
        );
    }

}
