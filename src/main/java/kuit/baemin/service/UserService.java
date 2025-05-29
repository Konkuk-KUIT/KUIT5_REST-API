package kuit.baemin.service;

import kuit.baemin.domain.User;
import kuit.baemin.dto.GetUserResponse;
import kuit.baemin.dto.PasswordChangeRequest;
import kuit.baemin.dto.SignupRequest;
import kuit.baemin.repository.UserRepository;
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
public class UserService {

    private final UserRepository userRepository;

    /**
     * 회원 가입 API
     * @param signupRequest
     * @return
     */
    @Transactional
    public User save(SignupRequest signupRequest) {
        return userRepository.save(new User(signupRequest.getEmail(), signupRequest.getPassword()));
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
        User user = userRepository.findById(id);
        userRepository.updatePassword(id, passwordChangeRequest.getNewPassword());
        user.setPassword(passwordChangeRequest.getNewPassword());
        return user;
    }

    /**
     * 회원 정보 조회 API
     * @param id
     * @return
     */
    @Transactional
    public GetUserResponse findById(Long id) {
        User user = userRepository.findById(id);
        return new GetUserResponse(user.getEmail(),
                user.getNickname(),
                user.getPhoneNumber(),
                user.getGrade(),
                user.getProfileImage());
    }

    /**
     * 회원 정보 삭제 API
     */
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
