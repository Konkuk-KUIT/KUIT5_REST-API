package kuit.baemin.service;

import kuit.baemin.domain.user.User;
import kuit.baemin.exception.BusinessException;
import kuit.baemin.repository.UserRepository;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final UserRepository userRepository;

    @Transactional
    public User login(String email, String password){
        return userRepository.findByEmail(email)
                // 조회된 사용자의 비밀번호가 입력된 비밀번호와 일치하는지.
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new BusinessException(BaseResponseStatus.LOGIN_FAILED));
    }
}
