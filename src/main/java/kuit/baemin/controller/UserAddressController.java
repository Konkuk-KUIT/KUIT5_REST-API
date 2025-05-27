package kuit.baemin.controller;

import jakarta.validation.Valid;
import kuit.baemin.dto.request.UserAddressRequest;
import kuit.baemin.dto.response.UserAddressResponse;
import kuit.baemin.service.UserAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-addresses")
@RequiredArgsConstructor
public class UserAddressController {

    private final UserAddressService service;

    @PostMapping
    public ResponseEntity<UserAddressResponse> createAddress(
            @RequestBody @Valid UserAddressRequest request) {

        Long userId = 1L; // 인증된 사용자 ID
        UserAddressResponse response = service.save(request, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
