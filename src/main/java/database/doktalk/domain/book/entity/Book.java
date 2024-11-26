package database.doktalk.domain.book.entity;

import database.doktalk.common.global.BaseEntity;
import database.doktalk.domain.journal.entity.Journal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Book extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookName;

    private String publisher;

    private String writer;

    private String story;

    private String genre;

    private String coverImage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Journal journal;
}
