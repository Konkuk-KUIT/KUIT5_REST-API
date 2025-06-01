package kuit.baemin.controller;

import jakarta.validation.Valid;
import kuit.baemin.domain.address.Addresses;
import kuit.baemin.dto.request.address.AddAddressRequest;
import kuit.baemin.dto.request.address.AddUserAddressRequest;
import kuit.baemin.service.AddressService;
import kuit.baemin.service.UserAddressService;
import kuit.baemin.utils.BaseResponse;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;
    private final UserAddressService userAddressService;

    @PostMapping("/addresses")
    public BaseResponse<Addresses> addAddress(@RequestBody @Valid AddAddressRequest addAddressRequest) {
        Addresses addresses = addressService.addAddress(addAddressRequest);
        return new BaseResponse<>(addresses);
    }

    @PostMapping("/users/{userId}/addresses")
    public BaseResponse<Addresses> setUserAddress(@PathVariable Long userId,
                                                  @RequestBody @Valid AddUserAddressRequest addUserAddressRequest) {
        log.info("setUserAddress request - userId : {}, addressId : {}, addressType : {}",
                userId,
                addUserAddressRequest.getAddressId(),
                addUserAddressRequest.getAddressType());
        userAddressService.setUserAddress(userId, addUserAddressRequest);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }
}
