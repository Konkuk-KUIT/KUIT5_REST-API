package kuit.baemin.service;

import kuit.baemin.domain.user.User;
import kuit.baemin.dto.request.LoginRequest;
import kuit.baemin.dto.response.UserResponse;
import kuit.baemin.dto.request.user.PasswordChangeRequest;
import kuit.baemin.dto.request.user.SignupRequest;
import kuit.baemin.exception.BusinessException;
import kuit.baemin.repository.UserRepository;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 가입 API
     * @param signupRequest
     * @return
     */
    @Transactional
    public User save(SignupRequest signupRequest) {
        String encodedPassword = passwordEncoder.encode(signupRequest.getPassword());
        return userRepository.save(new User(signupRequest.getEmail(), encodedPassword, signupRequest.getPhoneNumber(), signupRequest.getNickname()));
    }

    private static void validateEmail() {
        log.error("이메일 검증 오류");
        throw new RuntimeException();
    }

    /**
     * 회원 비번 변경 API
     * @param id
     * @param passwordChangeRequest
     * @return
     */
    // db에 저장하고, 자바 메모리 유저 객체의 바뀐 값을 반영하기.
    @Transactional
    public User changePassword(Long id, PasswordChangeRequest passwordChangeRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(BaseResponseStatus.USER_NOT_FOUND));
        // 먼저 current_password가 맞는지
        if (!passwordEncoder.matches(passwordChangeRequest.getCurrentPassword(), user.getPassword())) {
            throw new BusinessException(BaseResponseStatus.NON_MATCH_PASSWORD);
        }

        if (passwordEncoder.matches(passwordChangeRequest.getNewPassword(), user.getPassword())) {
            throw new BusinessException(BaseResponseStatus.SAME_PASSWORD);
        }

        //이제 업데이트
        String encodedNewPassword = passwordEncoder.encode(passwordChangeRequest.getNewPassword());
        userRepository.updatePassword(id, encodedNewPassword);
        user.setPassword(encodedNewPassword);
        return user;
    }
    /**
     * 회원 정보 조회 API
     * @param id
     * @return
     */
    @Transactional
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(BaseResponseStatus.USER_NOT_FOUND));
        return new UserResponse(user.getEmail(),
                user.getNickname(),
                user.getPhoneNumber(),
                user.getGrade().getKrName(),
                user.getProfileImage());
    }

    /**
     * 회원 정보 삭제 API
     */
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public User validateLogin(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BusinessException(BaseResponseStatus.USER_NOT_FOUND));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BusinessException(BaseResponseStatus.NON_MATCH_PASSWORD);
        }

        return user;
    }
}
