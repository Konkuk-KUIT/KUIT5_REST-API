package kuit.baemin.service.UserService;

import kuit.baemin.domain.Restaurant;
import kuit.baemin.domain.User;
import kuit.baemin.dto.request.SignupRequest;
import kuit.baemin.dto.response.RestaurantResponse;
import kuit.baemin.repository.UserRepository.UserRepository;
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

    private final UserRepository userRepository;

    @Transactional
    public User save(SignupRequest signupRequest) {
        return userRepository.save(new User(signupRequest.getEmail(), signupRequest.getPassword()));
    }

    private static void validateEmail() {
        log.error("이메일 검증 오류");
        throw new RuntimeException();
    }

    @Transactional
    public List<RestaurantResponse> getFavoriteRestaurants(Long userId) {
        return userRepository.findFavoriteRestaurantsByUserId(userId);
    }
}
