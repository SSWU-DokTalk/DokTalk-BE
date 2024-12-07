package database.doktalk.domain.user.controller;

import database.doktalk.common.global.BaseResponse;
import database.doktalk.domain.user.dto.request.UserSignUpRequest;
import database.doktalk.domain.user.dto.request.UserSingInRequest;
import database.doktalk.domain.user.dto.response.UserIdResponse;
import database.doktalk.domain.user.dto.response.UserMyPageResponse;
import database.doktalk.domain.user.service.UserService;
import database.doktalk.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "유저 API", description = "유저 관련 API")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 회원가입
    @PostMapping("/signup")
    public BaseResponse<UserIdResponse> signUp(@RequestBody UserSignUpRequest request) {
        return BaseResponse.onSuccess(userService.signUp(request));
    }

    // 로그인
    @PostMapping("/signin")
    public BaseResponse<UserIdResponse> signIn(@RequestBody UserSingInRequest request) {
        return BaseResponse.onSuccess(userService.signIn(request));
    }

    // 마이페이지
    @GetMapping("/mypage")
    public BaseResponse<UserMyPageResponse> getMyPage(@RequestParam String userId) {
        return BaseResponse.onSuccess(userService.getUserDetails(userId));
    }

}
