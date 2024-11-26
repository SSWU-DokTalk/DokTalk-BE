package database.doktalk.domain.user.controller;

import database.doktalk.common.global.BaseResponse;
import database.doktalk.domain.user.dto.request.UserSignUpRequest;
import database.doktalk.domain.user.dto.response.UserIdResponse;
import database.doktalk.domain.user.entity.User;
import database.doktalk.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "유저 API", description = "유저 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    // 회원가입
    @Operation(summary = "회원가입 API")
    @PostMapping("/signup")
    public ResponseEntity<UserIdResponse> signUp(@RequestBody UserSignUpRequest request) {
        UserIdResponse response = userService.signUp(request);
        return ResponseEntity.ok(response);
    }

    // 로그인
    @Operation(summary = "로그인 API")
    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestParam String userId, @RequestParam String password) {
        boolean isLoggedIn = userService.signIn(userId, password);
        if (isLoggedIn) {
            return ResponseEntity.ok("로그인 성공");
        } else {
            return ResponseEntity.status(401).body("로그인 실패: 아이디 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    // 마이페이지
    @Operation(summary = "마이페이지 조회 API")
    @GetMapping("/mypage")
    public ResponseEntity<User> getMyPage(@RequestParam String userId) {
        User user = userService.getUserDetails(userId);
        return ResponseEntity.ok(user);
    }
}
