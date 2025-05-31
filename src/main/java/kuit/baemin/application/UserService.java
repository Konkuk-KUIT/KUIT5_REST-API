package kuit.baemin.application;

import kuit.baemin.domain.User;
import kuit.baemin.dto.request.LoginRequest;
import kuit.baemin.dto.request.UserRequest;
import kuit.baemin.dto.response.LoginResponse;
import kuit.baemin.dto.response.UserResponse;
import kuit.baemin.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse userSignup(UserRequest request) {
        if (userRepository.findByUserId(request.getUserId()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 유저 아이디입니다.");
        }

        User user = User.builder()
                .userId(request.getUserId())
                .email(request.getEmail())
                .password(request.getPassword()) // 실제로는 암호화해줘야하지만 생략
                .phoneNumber(request.getPhoneNumber())
                .nickname(request.getNickname())
                .profile_image(request.getProfile_image())
                .created_at(LocalDate.now())
                .build();

        userRepository.save(user);

        return UserResponse.from(user);
    }

    public LoginResponse userLogin(LoginRequest loginRequest) {

        User user = userRepository.findByUserId(loginRequest.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아이디입니다."));

        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 잘못되었습니다.");
        }

        return LoginResponse.from(user);
    }
}
