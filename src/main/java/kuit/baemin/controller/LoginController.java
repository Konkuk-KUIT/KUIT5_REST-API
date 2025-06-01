package kuit.baemin.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kuit.baemin.common.auth.JwtInfo;
import kuit.baemin.common.auth.JwtTokenProvider;
import kuit.baemin.domain.user.User;
import kuit.baemin.dto.request.LoginRequest;
import kuit.baemin.service.UserService;
import kuit.baemin.utils.BaseResponse;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public BaseResponse<JwtInfo> login(@Valid @RequestBody LoginRequest loginRequest){
        User user = userService.validateLogin(loginRequest);
        JwtInfo jwtInfo = jwtTokenProvider.createToken(user.getEmail(), String.valueOf(user.getUserId()));
        return new BaseResponse<>(jwtInfo);
    }

    @PostMapping("/logout")
    public BaseResponse<User> logout(HttpServletRequest request){
        // true면 새 세션을 생성.
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
