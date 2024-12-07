package database.doktalk.domain.user.service;

import database.doktalk.common.global.exception.CustomApiException;
import database.doktalk.common.global.exception.ErrorCode;
import database.doktalk.domain.user.dto.request.UserSignUpRequest;
import database.doktalk.domain.user.dto.response.UserIdResponse;
import database.doktalk.domain.user.dto.response.UserMyPageResponse;
import database.doktalk.domain.user.entity.User;
import database.doktalk.domain.user.mapper.UserMapper;
import database.doktalk.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserIdResponse signUp(UserSignUpRequest request) {
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        return new UserIdResponse(savedUser.getId(), savedUser.getUserId());
    }

    @Override
    public UserIdResponse signIn(String userId, String password) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new CustomApiException(ErrorCode.USER_NOT_FOUND));
        return new UserIdResponse(user.getId(), user.getUserId());
    }

    @Override
    public UserMyPageResponse getUserDetails(String userId) {
        User user = userRepository.findByUserId(userId).orElseThrow(() -> new CustomApiException(ErrorCode.USER_NOT_FOUND));

        String imageUrl = user.getImage() != null ? user.getImage().getUrl() : null;

        return new UserMyPageResponse(
                user.getName(),
                user.getUserId(),
                user.getPhoneNumber(),
                user.getImage() != null ? user.getImage().getUrl() : null // Handle null image case
        );
    }



}
