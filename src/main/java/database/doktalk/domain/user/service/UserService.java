package database.doktalk.domain.user.service;

import database.doktalk.domain.user.dto.request.UserSignUpRequest;
import database.doktalk.domain.user.dto.request.UserSingInRequest;
import database.doktalk.domain.user.dto.response.UserIdResponse;
import database.doktalk.domain.user.entity.User;
import database.doktalk.domain.user.dto.response.UserMyPageResponse;

public interface UserService {

    // 회원가입
    UserIdResponse signUp(UserSignUpRequest request);

    // 로그인
    UserIdResponse signIn(UserSingInRequest request);

    // 마이페이지 조회
    UserMyPageResponse getUserDetails(String userId);

    // ID로 사용자 조회
//    User getUserById(Long id);
}
