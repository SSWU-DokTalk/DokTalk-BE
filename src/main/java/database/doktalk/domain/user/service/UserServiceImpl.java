package database.doktalk.domain.user.service;

import database.doktalk.domain.user.dto.request.UserSignUpRequest;
import database.doktalk.domain.user.dto.response.UserIdResponse;
import database.doktalk.domain.user.entity.User;
import database.doktalk.domain.user.mapper.UserMapper;
import database.doktalk.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserIdResponse signUp(UserSignUpRequest request) {
        User user = userMapper.toEntity(request);
        User savedUser = userRepository.save(user);
        return new UserIdResponse(savedUser.getId(), savedUser.getUserId());
    }

    @Override
    public boolean signIn(String userId, String password) {
        Optional<User> user = userRepository.findByUserId(userId); // Use Optional
        return user.map(u -> u.getPassword().equals(password)).orElse(false); // Safe check
    }

    @Override
    public User getUserDetails(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId)); // Explicit exception handling
    }
}
