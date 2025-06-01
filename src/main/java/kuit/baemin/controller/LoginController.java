package kuit.baemin.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import kuit.baemin.domain.user.User;
import kuit.baemin.dto.request.LoginRequest;
import kuit.baemin.service.LoginService;
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
    public final LoginService loginService;

    // todo : 수동 로그인 -> Spring Security 사용
    @PostMapping("/login")
    public BaseResponse<User> login(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request){
        User user = loginService.login(loginRequest.getEmail(), loginRequest.getPassword());
        request.getSession().setAttribute("loginUser", user);
        return new BaseResponse<>(user);
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
