package kuit.baemin.controller;

import jakarta.validation.Valid;
import kuit.baemin.dto.request.LoginRequest;
import kuit.baemin.service.LoginService;
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
    public String login(@Valid @RequestBody LoginRequest loginRequest){
        return loginService.login(loginRequest.getEmail(), loginRequest.getPassword()).getEmail();
    }
}
