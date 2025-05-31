package kuit.baemin.controller;

import kuit.baemin.domain.User;
import kuit.baemin.dto.SignupRequest;
import kuit.baemin.service.UserServiceV4;
import kuit.baemin.utils.BaseResponse;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Slf4j
//@Controller
@RestController
public class UserController {

    private final UserServiceV4 usersService;


    //  기본
//    @PostMapping("/users")
//    @ResponseBody
    public String signup1 (@Validated @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        log.info("signup request - email : {}, password : {}, confirm_password: {}, nickname: {}, phoneNumber: {}",
                signupRequest.getEmail(),
                signupRequest.getPassword(),
                signupRequest.getConfirmPassword(),
                signupRequest.getNickname(),
                signupRequest.getPhoneNumber()
        );

        if (bindingResult.hasErrors()) {
            throw new RuntimeException();
        }

        return "ok";
    }

    // 요청 파라미터로 HttpEntity로 매핑
//    @PostMapping("/users")
//    @ResponseBody
    public String signup2 (HttpEntity<SignupRequest> signupRequest) {
        log.info("signup request - email : {}, password : {}, confirm_password: {}, nickname: {}, phoneNumber: {}",
                signupRequest.getBody().getEmail(),
                signupRequest.getBody().getPassword(),
                signupRequest.getBody().getConfirmPassword(),
                signupRequest.getBody().getNickname(),
                signupRequest.getBody().getPhoneNumber()
        );
        return "ok";
    }

    // 요청 파라미터와 응답 타입으로 HttpEntity 사용
//    @PostMapping("/users")
    public HttpEntity<String> signup3 (HttpEntity<SignupRequest> signupRequest) {
        log.info("signup request - email : {}, password : {}, confirm_password: {}, nickname: {}, phoneNumber: {}",
                signupRequest.getBody().getEmail(),
                signupRequest.getBody().getPassword(),
                signupRequest.getBody().getConfirmPassword(),
                signupRequest.getBody().getNickname(),
                signupRequest.getBody().getPhoneNumber()
        );
        return new HttpEntity<>("ok");
    }

    //  @RequestBody 제거
//    @PostMapping("/users")
//    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String signup4 (SignupRequest signupRequest) {
        log.info("signup request - email : {}, password : {}, confirm_password: {}, nickname: {}, phoneNumber: {}",
                signupRequest.getEmail(),
                signupRequest.getPassword(),
                signupRequest.getConfirmPassword(),
                signupRequest.getNickname(),
                signupRequest.getPhoneNumber()
        );

        return "no";
    }

    // 객체 to json
//    @PostMapping("/users")
//    @ResponseBody
    public BaseResponse<User> signup5 (@RequestBody SignupRequest signupRequest) {
        log.info("signup request - email : {}, password : {}, confirm_password: {}, nickname: {}, phoneNumber: {}",
                signupRequest.getEmail(),
                signupRequest.getPassword(),
                signupRequest.getConfirmPassword(),
                signupRequest.getNickname(),
                signupRequest.getPhoneNumber()
        );

        User user = new User(signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getPhoneNumber(),signupRequest.getNickname());

        return new BaseResponse<>(user);
    }

    // 객체 to json
    @PostMapping("/users")
//    @ResponseBody
    public BaseResponse<User> signup (@Validated @RequestBody SignupRequest signupRequest) {
        log.info("signup request - email : {}, password : {}, confirm_password: {}, nickname: {}, phoneNumber: {}",
                signupRequest.getEmail(),
                signupRequest.getPassword(),
                signupRequest.getConfirmPassword(),
                signupRequest.getNickname(),
                signupRequest.getPhoneNumber()
        );

        User user = usersService.save(signupRequest);
        return new BaseResponse<>(user);
    }

    //  오류 응답
//    @PostMapping("/users")
//    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> signup6 (SignupRequest signupRequest) {
        log.info("signup request - email : {}, password : {}, confirm_password: {}, nickname: {}, phoneNumber: {}",
                signupRequest.getEmail(),
                signupRequest.getPassword(),
                signupRequest.getConfirmPassword(),
                signupRequest.getNickname(),
                signupRequest.getPhoneNumber()
        );

        return new BaseResponse<>(BaseResponseStatus.DUPLICATED_EMAIL);
    }


}
