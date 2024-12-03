package database.doktalk.domain.discussionreview.entity;

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
public class DiscussionReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String review;

    private LocalDateTime createdAt;

    private int likeCount = 0;
    
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Discussion discussion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
}
