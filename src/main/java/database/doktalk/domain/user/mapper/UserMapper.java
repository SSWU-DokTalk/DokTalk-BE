package database.doktalk.domain.user.mapper;

import database.doktalk.domain.user.dto.request.UserSignUpRequest;
import database.doktalk.domain.user.dto.response.UserIdResponse;
import database.doktalk.domain.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toUser(UserSignUpRequest request){
        return User.builder()
                .userId(request.getUserId())
                .password(request.getPassword())
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .build();
    }

    public UserIdResponse toUserIdResponse(User user){
        return UserIdResponse.builder()
                .id(user.getId())
                .userId(user.getUserId())
                .build();
    }
}
