package database.doktalk.domain.discussion.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import database.doktalk.domain.user.entity.User;
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
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean voted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
//    @JsonBackReference
    private Discussion discussion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

}
