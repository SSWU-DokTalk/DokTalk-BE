package database.doktalk.domain.user.entity;

import database.doktalk.common.global.BaseEntity;
import database.doktalk.domain.discussion.entity.Vote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User extends BaseEntity {

    // Getter and Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id; //사용자 PK

    @Column(nullable = false, unique = true, length = 50)
    private String userId; // 사용자 아이디

    @Column(nullable = false)
    private String password; // 비밀번호

    @Column(nullable = false, length = 100)
    private String name; // 사용자 이름

    @Column(nullable = false, length = 15)
    private String phoneNumber; // 전화번호

    @OneToOne(cascade = CascadeType.ALL)
    private UserImage image;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Vote> votes = new ArrayList<>();

    public void setUserId(Long id){
        this.Id=Id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setImage(UserImage image) {
        this.image = image;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
