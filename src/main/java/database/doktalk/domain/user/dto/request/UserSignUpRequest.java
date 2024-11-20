package database.doktalk.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpRequest {
    private String userId;
    private String password;
    private String name;
    private String phoneNumber;
    //TODO 프로필 이미지 추가하기
}
