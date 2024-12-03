package database.doktalk.domain.user.service;

import database.doktalk.domain.user.dto.request.UserSignUpRequest;
import database.doktalk.domain.user.dto.response.UserIdResponse;
import database.doktalk.domain.user.entity.User;
import database.doktalk.domain.user.repository.UserRepository;

public interface UserService {

//    private final UserRepository userRepository;


    // 회원가입
    UserIdResponse signUp(UserSignUpRequest request);

    // 로그인
    boolean signIn(String userId, String password);

    // 마이페이지 조회
    User getUserDetails(String userId);
}
