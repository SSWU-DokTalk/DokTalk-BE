package database.doktalk.domain.diary.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 글 ID

    @Column(nullable = false)
    private String title; // 감상문 제목

    @Column(nullable = false)
    private Long userId; // 작성자 ID

    @Lob
    @Column(nullable = false)
    private String content; // 감상문 내용

    // 책 관련 정보 (필수)
    @Column(nullable = false)
    private String bookTitle; // 책 제목

    @Column(nullable = false)
    private String author; // 지은이

    @Column(nullable = false)
    private String publisher; // 출판사

    @Column(nullable = true)
    private String bookCoverUrl; // 책 표지 URL

    @Column(updatable = false)
    private LocalDateTime createdAt; // 생성일

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    private int likeCount = 0;
}
