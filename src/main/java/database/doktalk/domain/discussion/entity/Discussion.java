package database.doktalk.domain.discussion.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import database.doktalk.common.global.BaseEntity;
import database.doktalk.domain.discussionreview.entity.DiscussionReview;
import database.doktalk.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Discussion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // 글 제목

    @Column(nullable = false)
    private String bookTitle; // 책 제목

    @Column(nullable = false)
    private String author; // 지은이

    @Column(nullable = false)
    private String publisher; // 출판사

    @Lob
    @Column(nullable = false)
    private String content; // 글 내용

    private int approval = 0;

    private int opposite = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL)
    private List<DiscussionReview> discussionReviews = new ArrayList<>();

    @OneToMany(mappedBy = "discussion", cascade = CascadeType.ALL)
//    @JsonManagedReference
    private List<Vote> votes = new ArrayList<>();


    public Long getUserId() {
        return this.user != null ? this.user.getId() : null;
    }


}
