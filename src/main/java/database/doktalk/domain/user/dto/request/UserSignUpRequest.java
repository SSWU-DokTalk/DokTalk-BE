package database.doktalk.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequest {
    private String userId;
    private String password;
    private String name;
    private String phoneNumber;
    private String imageUrl; //TODO 프로필 이미지 추가하기
}

