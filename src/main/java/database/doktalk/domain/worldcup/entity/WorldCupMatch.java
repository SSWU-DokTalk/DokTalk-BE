package database.doktalk.domain.worldcup.entity;

import database.doktalk.common.global.BaseEntity;
import database.doktalk.domain.book.entity.Book;
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
public class WorldCupMatch extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int roundNumber;

    private String winBookName;

    @ManyToOne
    @JoinColumn
    private WorldCup worldCup;

    @ManyToOne
    @JoinColumn
    private Book book1;

    @ManyToOne
    @JoinColumn
    private Book book2;

    private void setWinBookName(String winBookName) {
        this.winBookName = winBookName;
    }
}
