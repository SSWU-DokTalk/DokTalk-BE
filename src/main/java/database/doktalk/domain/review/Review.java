package database.doktalk.domain.review;

import database.doktalk.domain.discussion.entity.Discussion;
import database.doktalk.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String review;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    private int likeCount = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Discussion discussion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
}
