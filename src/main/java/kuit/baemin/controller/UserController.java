package kuit.baemin.controller;

import kuit.baemin.domain.User;
import kuit.baemin.dto.GetUserResponse;
import kuit.baemin.dto.PasswordChangeRequest;
import kuit.baemin.dto.SignupRequest;
import kuit.baemin.service.UserService;
import kuit.baemin.utils.BaseResponse;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Slf4j
//@Controller
@RestController
public class UserController {

    private final UserService usersService;


    //  기본
//    @PostMapping("/users")
//    @ResponseBody
    // HTTP 요청의 JSON 바디 -> SignupRequest로 매핑. hasErrors로 번거로움
    public String signup1 (@Validated @RequestBody SignupRequest signupRequest, BindingResult bindingResult) {
        log.info("signup request - email : {}, password : {}, confirm_password : {}",
                signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getConfirmPassword());

        // Validator(SignupValidator)에서 에러가 발생 -> bindingResult에 담겨 있음.
        if (bindingResult.hasErrors()) {
            // 가장 첫번쨰 오류 메시지를 꺼내 Runtime으로 던지고,
            // controller advice의 @ExceptionHandler에서 잡아서
            // BaseResponseStatus.NON_MATCH_PASSWORD로 응답.
            throw new RuntimeException();
        }

        return "ok";
    }

    // 요청 파라미터로 HttpEntity로 매핑 (요청 바디 + 헤더 전체를 감싼 객체로 받는다) -> 헤더에 담긴 정보도 포함할때!
//    @PostMapping("/users")
//    @ResponseBody
    public String signup2 (HttpEntity<SignupRequest> signupRequest) {
        log.info("signup request - email : {}, password : {}",
                signupRequest.getBody().getEmail(), signupRequest.getBody().getPassword());

        return "ok";
    }

    // 요청 파라미터와 응답 타입으로 HttpEntity 사용 (원하는 HTTP 헤더 + 바디 형태로 보낸다 -> 상태코드, 헤더, 바디를 제어하기 쉬움)
//    @PostMapping("/users")
    public HttpEntity<String> signup3 (HttpEntity<SignupRequest> signupRequest) {
        log.info("signup request - email : {}, password : {}",
                signupRequest.getBody().getEmail(), signupRequest.getBody().getPassword());

        return new HttpEntity<>("ok");
    }

    //  @RequestBody 제거
//    @PostMapping("/users")
//    @ResponseBody
    // 고정된 바디 : 400 Bad Request 들어감. (JSON이 아님. 하지만 대부분 API에서는 JSON 들어감 -> not 실용적)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String signup4 (SignupRequest signupRequest) {
        log.info("signup request - email : {}, password : {}",
                signupRequest.getEmail(), signupRequest.getPassword());

        return "no";
    }

    // 객체 to json
//    @PostMapping("/users")
//    @ResponseBody
    // 요청 : JSON -> SignupRequest 객체 / 응답 : BaseResponse<User> (일관된 형식을 유지)
    public BaseResponse<User> signup5 (@RequestBody SignupRequest signupRequest) {
        log.info("signup request - email : {}, password : {}",
                signupRequest.getEmail(), signupRequest.getPassword());

        User user = new User(signupRequest.getEmail(), signupRequest.getPassword());

        return new BaseResponse<>(user);
    }

    // 객체 to json
    @PostMapping("/users")
//    @ResponseBody
    public BaseResponse<User> signup (@Validated @RequestBody SignupRequest signupRequest) {
        log.info("signup request - email : {}, password : {}",
                signupRequest.getEmail(), signupRequest.getPassword());

        User user = usersService.save(signupRequest);
        return new BaseResponse<>(user);
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

    @GetMapping("/users/{id}")
    public BaseResponse<GetUserResponse> getUser(@PathVariable Long id) {
        log.info("get user - id : {}", id);
        GetUserResponse user = usersService.findById(id);
        return new BaseResponse<>(user);
    }

    @PutMapping("/users/{id}/password")
    public BaseResponse<User> updatePassword(@PathVariable Long id,
                                             @Validated @RequestBody PasswordChangeRequest passwordChangeRequest) {
        log.info("update password - id : {}, password : {}, new_password : {}",
                id, passwordChangeRequest.getCurrentPassword(), passwordChangeRequest.getNewPassword());
        User user = usersService.changePassword(id, passwordChangeRequest);
        return new BaseResponse<>(user);
        // 일단 찾고, 수정한다.
    }

    @DeleteMapping("/users/{id}")
    public BaseResponse<Void> deleteUser(@PathVariable Long id) {
        log.info("delete user - id : {}", id);
        usersService.deleteById(id);
        return new BaseResponse<Void>((Void) null);
    }

//    // 회원 수정 API
//    @PatchMapping("/users/{id}")
//    public BaseResponse<User> updateUser(@PathVariable Long id, @Validated @RequestBody SignupRequest signupRequest) {
//        log.info("update user - id : {}, email : {}, password : {}",
//                id, signupRequest.getEmail(), signupRequest.getPassword());
//        // 일단 찾고, 수정한다.
//    }

}
