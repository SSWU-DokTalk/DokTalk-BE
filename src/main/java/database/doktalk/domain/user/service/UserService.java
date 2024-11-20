package database.doktalk.domain.user.service;

import database.doktalk.domain.user.dto.request.UserSignUpRequest;
import database.doktalk.domain.user.dto.response.UserIdResponse;

public interface UserService {

    UserIdResponse SignUp(UserSignUpRequest request); // 유저 회원가입
}
