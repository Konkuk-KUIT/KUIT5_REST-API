package kuit.baemin.api;

import kuit.baemin.application.UserService;
import kuit.baemin.dto.request.LoginRequest;
import kuit.baemin.dto.request.UserRequest;
import kuit.baemin.dto.response.LoginResponse;
import kuit.baemin.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserApi {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> userSignup(@RequestBody UserRequest request){
        UserResponse userResponse = userService.userSignup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> userLogin(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = userService.userLogin(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
