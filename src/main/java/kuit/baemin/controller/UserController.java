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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        log.info("signup request - email : {}, password : {}, confirm_password : {}",
                signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getConfirmPassword());

        if (bindingResult.hasErrors()) {
            throw new RuntimeException();
        }

        return "ok";
    }

    // 요청 파라미터로 HttpEntity로 매핑
//    @PostMapping("/users")
//    @ResponseBody
    public String signup2 (HttpEntity<SignupRequest> signupRequest) {
        log.info("signup request - email : {}, password : {}",
                signupRequest.getBody().getEmail(), signupRequest.getBody().getPassword());

        return "ok";
    }

    // 요청 파라미터와 응답 타입으로 HttpEntity 사용
//    @PostMapping("/users")
    public HttpEntity<String> signup3 (HttpEntity<SignupRequest> signupRequest) {
        log.info("signup request - email : {}, password : {}",
                signupRequest.getBody().getEmail(), signupRequest.getBody().getPassword());

        return new HttpEntity<>("ok");
    }

    //  @RequestBody 제거
//    @PostMapping("/users")
//    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String signup4 (SignupRequest signupRequest) {
        log.info("signup request - email : {}, password : {}",
                signupRequest.getEmail(), signupRequest.getPassword());

        return "no";
    }

    // 객체 to json
//    @PostMapping("/users")
//    @ResponseBody
    public BaseResponse<User> signup5 (@RequestBody SignupRequest signupRequest) {
        log.info("signup request - email : {}, password : {}",
                signupRequest.getEmail(), signupRequest.getPassword());

        User user = new User(signupRequest.getEmail(), signupRequest.getPassword(),signupRequest.getUserId());

        return new BaseResponse<>(user);
    }

    // 객체 to json
    @PostMapping("/users")
    @ResponseBody
    public BaseResponse<User> signup (@Validated @RequestBody SignupRequest signupRequest) {
        log.info("signup request - email : {}, password : {},id :{}",
                signupRequest.getEmail(), signupRequest.getPassword(),signupRequest.getUserId());

        User user = usersService.save(signupRequest);
        return new BaseResponse<>(user);
    }

    @GetMapping("/users")
    @ResponseBody
    public BaseResponse<List<User>> getAll () {
        log.info("getAll request");
        List<User> users = usersService.findAll();  // 서비스에서 모든 유저 가져오기
        log.info("총 사용자 수: {}", users.size());
        return new BaseResponse<>(users);
    }
    @DeleteMapping("/users/{id}")
    @ResponseBody
    public BaseResponse<Boolean> deleteUser (@PathVariable String id) {
        log.info("deleteUser request - id : {}", id);
        return new BaseResponse<>(usersService.delete(id));
    }
    @PatchMapping("/users")
    @ResponseBody
    public BaseResponse<String> updateUser (@RequestBody User user) {
        return new BaseResponse<>(usersService.update(user));
    }

    //  오류 응답
//    @PostMapping("/users")
//    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse<Object> signup6 (SignupRequest signupRequest) {
        log.info("signup request - email : {}, password : {}",
                signupRequest.getEmail(), signupRequest.getPassword());

        return new BaseResponse<>(BaseResponseStatus.DUPLICATED_EMAIL);
    }


}
