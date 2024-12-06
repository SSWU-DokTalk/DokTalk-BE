package database.doktalk.domain.diary.mapper;

import database.doktalk.domain.diary.dto.DiaryListDTO;
import database.doktalk.domain.diary.dto.request.DiaryRequest;
import database.doktalk.domain.diary.dto.response.DiaryDetailResponse;
import database.doktalk.domain.diary.entity.Diary;
import database.doktalk.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class DiaryMapper {

    // DiaryRequest -> Diary 엔티티 변환
    public Diary toDiary(DiaryRequest request, User user) {
        return Diary.builder()
                .title(request.getTitle())
                .bookTitle(request.getBookTitle())
                .author(request.getAuthor())
                .content(request.getContent())
                .publisher(request.getPublisher())
                .bookCoverUrl(request.getBookCoverUrl())
                .user(user)
                .build();
    }

    // Diary 엔티티 -> DiaryDetailResponse 변환
    public DiaryDetailResponse toDiaryDetailResponse(Diary diary) {
        return DiaryDetailResponse.builder()
                .title(diary.getTitle())
                .bookTitle(diary.getBookTitle())
                .author(diary.getAuthor())
                .content(diary.getContent())
                .publisher(diary.getPublisher())
                .bookCoverUrl(diary.getBookCoverUrl())
                .likeCount(diary.getLikeCount())
                .build();
    }

    public DiaryListDTO toDiaryListDTO(Diary diary) {
        return new DiaryListDTO(
                diary.getId(),
                diary.getTitle(),
                diary.getBookTitle(),
                diary.getCreatedAt().toLocalDate()  // 작성일 처리
        );
    }
}
