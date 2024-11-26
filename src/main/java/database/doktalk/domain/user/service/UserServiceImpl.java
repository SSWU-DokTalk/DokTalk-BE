package database.doktalk.domain.user.service;

import database.doktalk.domain.user.dto.request.UserSignUpRequest;
import database.doktalk.domain.user.dto.response.UserIdResponse;
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
    public boolean signIn(String userId, String password) {
        User user = userRepository.findByUserId(userId);
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public User getUserDetails(String userId) {
        return userRepository.findByUserId(userId);
    }
}
