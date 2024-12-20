package database.doktalk.domain.diary.entity;

import database.doktalk.domain.user.entity.User;
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

    @Column(nullable = false)
    private LocalDateTime createdAt; // 생성일

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private User user; // 작성자 ID

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();  // 현재 시간으로 설정
        }
    }

    @Column(nullable = false)
    private Integer likeCount = 0;
}
