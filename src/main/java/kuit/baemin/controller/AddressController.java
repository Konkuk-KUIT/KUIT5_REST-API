package kuit.baemin.controller;

import kuit.baemin.dto.AddressRequest;
import kuit.baemin.dto.UserAddressResponse;
import kuit.baemin.service.AddressService;
import kuit.baemin.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/users/{userId}/address")
    public BaseResponse<Map<String, Long>> addAddress(@PathVariable("userId")Long userId, @RequestBody AddressRequest request) {
        Long addressId = addressService.addAddress(userId, request);
        return new BaseResponse<>(Map.of("addressId", addressId));
    }

    @GetMapping("/users/{userId}/address")
    public BaseResponse<List<UserAddressResponse>> getAddresses(@PathVariable("userId") Long userId) {
        List<UserAddressResponse> addresses = addressService.getUserAddresses(userId);
        return new BaseResponse<>(addresses);
    }

}
