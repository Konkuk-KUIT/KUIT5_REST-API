package kuit.baemin.service;

import kuit.baemin.domain.User;
import kuit.baemin.dto.SignupRequest;
import kuit.baemin.repository.UserRepository;
import kuit.baemin.repository.UserRepositoryV6;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 예외 누수 문제 해결
 * SQLException 제거
 * MemberRepository 인터페이스 의존
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceV4 {
    private final UserRepositoryV6 userRepository;

    @Transactional
    public User save(SignupRequest signupRequest) {
        return userRepository.save(new User(signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getUserId()));
    }

    private static void validateEmail() {
        log.error("이메일 검증 오류");
        throw new RuntimeException();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean delete(String id) {
        if(userRepository.hasUserId(id)){
            return userRepository.deleteUserbyId(id);
        }
        return false;
    }

    public String update(User user) {
        if(userRepository.hasUserId(user.getUserId())){
            return userRepository.updateUser(user);
        }
        return "유저없음";
    }
}
