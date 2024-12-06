package database.doktalk.domain.diary.service;

import database.doktalk.common.global.exception.CustomApiException;
import database.doktalk.common.global.exception.ErrorCode;
import database.doktalk.domain.book.entity.Book;
import database.doktalk.domain.book.repository.BookRepository;
import database.doktalk.domain.diary.dto.DiaryDetailDTO;
import database.doktalk.domain.diary.dto.DiaryListDTO;
import database.doktalk.domain.diary.dto.request.DiaryRequest;
import database.doktalk.domain.diary.dto.response.DiaryDetailResponse;
import database.doktalk.domain.diary.dto.response.DiaryIdResponse;
import database.doktalk.domain.diary.entity.Diary;
import database.doktalk.domain.diary.mapper.DiaryMapper;
import database.doktalk.domain.diary.repository.DiaryRepository;
import database.doktalk.domain.discussion.dto.DiscussionListDTO;
import database.doktalk.domain.discussion.dto.response.DiscussionDetailResponse;
import database.doktalk.domain.discussion.dto.response.DiscussionIdResponse;
import database.doktalk.domain.discussion.entity.Discussion;
import database.doktalk.domain.discussion.mapper.DiscussionMapper;
import database.doktalk.domain.user.entity.User;
import database.doktalk.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final DiaryMapper diaryMapper;


    //책 검색
    public List<Book> searchBooks(String keyword) {
        return bookRepository.findBooksByKeyword(keyword);
    }

    //기록장 생성
    public DiaryIdResponse saveDiary(DiaryRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(()-> new CustomApiException(ErrorCode.USER_NOT_ADMIN));
        Diary newDiary = diaryMapper.toDiary(request, user);
        diaryRepository.save(newDiary);
        return new DiaryIdResponse(newDiary.getId());
    }

    //기록장 수정
    public Diary updateDiary(Long diaryId, String title, String bookTitle, String author, String publisher, String bookCoverUrl, String content) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("Diary not found with id: " + diaryId));

        if (title != null) diary.setTitle(title);
        if (bookTitle != null) diary.setBookTitle(bookTitle);
        if (author != null) diary.setAuthor(author);
        if (publisher != null) diary.setPublisher(publisher);
        if (bookCoverUrl != null) diary.setBookCoverUrl(bookCoverUrl);
        if (content != null) diary.setContent(content);

        return diaryRepository.save(diary);
    }

    //기록장 삭제
    public void deleteDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("Diary not found with id: " + diaryId));
        diaryRepository.delete(diary);
    }

    //목록 조회
    public List<DiaryListDTO> getUserDiaryList(Long userId) {
        List<Diary> diaries = diaryRepository.findByUserId(userId); // 사용자 ID로 다이어리 목록 조회
        return diaries.stream()
                .map(diary -> diaryMapper.toDiaryListDTO(diary))  // Diary -> DiaryListDTO로 변환
                .collect(Collectors.toList());
    }

    //상세 조회
    public DiaryDetailResponse getDiaryDetail(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new CustomApiException(ErrorCode.DIARY_NOT_FOUND));
        return diaryMapper.toDiaryDetailResponse(diary);    }

    //좋아요 기능
    @Transactional
    public Diary likeDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("Diary not found with id: " + diaryId));

        diary.setLikeCount(diary.getLikeCount() + 1); // 좋아요 증가
        return diaryRepository.save(diary);
    }
}
