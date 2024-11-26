package database.doktalk.domain.user.mapper;

import database.doktalk.domain.user.dto.request.UserSignUpRequest;
import database.doktalk.domain.user.dto.response.UserIdResponse;
import database.doktalk.domain.user.entity.User;
import database.doktalk.domain.user.entity.UserImage;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserSignUpRequest request) {
        UserImage userImage = UserImage.builder()
                .url(request.getImageUrl())
                .build();

        return User.builder()
                .userId(request.getUserId())
                .password(request.getPassword())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .image(userImage)
                .build();
    }
}
