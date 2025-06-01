package kuit.baemin.service;

import kuit.baemin.domain.User;
import kuit.baemin.dto.SignupRequest;
import kuit.baemin.repository.UserRepository;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 예외 누수 문제 해결
 * SQLException 제거
 * MemberRepository 인터페이스 의존
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceV4 {

    private final UserRepository userRepository;

    @Transactional
    public User save(SignupRequest signupRequest) {
        if (existsByEmail(signupRequest.getEmail())) {
            throw new RuntimeException(BaseResponseStatus.DUPLICATED_EMAIL.getResponseMessage());
        }

        return userRepository.save(new User(
                signupRequest.getEmail(),
                signupRequest.getPassword(),
                signupRequest.getPhoneNumber(),
                signupRequest.getNickname()

        ));
    }

    @Transactional(readOnly = true)
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException(BaseResponseStatus.LOGIN_FAILED.getResponseMessage()));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException(BaseResponseStatus.LOGIN_FAILED.getResponseMessage());
        }

        return user;
    }


    private static void validateEmail() {
        log.error("이메일 검증 오류");
        throw new RuntimeException();
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
