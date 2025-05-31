package kuit.baemin.api;

import kuit.baemin.application.UserService;
import kuit.baemin.dto.request.LoginRequest;
import kuit.baemin.dto.request.UserRequest;
import kuit.baemin.dto.response.LoginResponse;
import kuit.baemin.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class UserApi {
    private final UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> userSignup(@RequestBody UserRequest request){
        UserResponse userResponse = userService.userSignup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUserInfo(@PathVariable Long userId) {
        UserResponse response = userService.getUserInfo(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserResponse> updateUserInfo(@PathVariable Long userId, @RequestBody UserRequest userRequest) {
        UserResponse response = userService.updateUserInfo(userId, userRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> userLogin(@RequestBody LoginRequest loginRequest){
        LoginResponse loginResponse = userService.userLogin(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
