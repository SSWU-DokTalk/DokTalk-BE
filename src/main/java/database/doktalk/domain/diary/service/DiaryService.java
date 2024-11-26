package database.doktalk.domain.diary.service;

import database.doktalk.domain.book.entity.Book;
import database.doktalk.domain.book.repository.BookRepository;
import database.doktalk.domain.diary.dto.DiaryDetailDTO;
import database.doktalk.domain.diary.dto.DiaryListDTO;
import database.doktalk.domain.diary.entity.Diary;
import database.doktalk.domain.diary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@RequiredArgsConstructor
public class DiaryService {


    private final DiaryRepository diaryRepository;



    // 독서감상문 저장
    public Diary saveDiary(Diary diary) {
        return diaryRepository.save(diary);
    }


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

    public void deleteDiary(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
                .orElseThrow(() -> new IllegalArgumentException("Diary not found with id: " + diaryId));

        diaryRepository.delete(diary);
    }



    // 사용자별 독서감상문 목록 조회
    public List<DiaryListDTO> getUserDiaryList(Long userId) {
        return diaryRepository.findDiaryListByUserId(userId);
    }

    // 글 상세조회
    public DiaryDetailDTO getDiaryDetail(Long diaryId) {
        Diary diary = diaryRepository.findById(diaryId)
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
