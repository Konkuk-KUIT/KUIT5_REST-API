package kuit.baemin.controller;

import jakarta.validation.Valid;
import kuit.baemin.domain.address.Addresses;
import kuit.baemin.dto.request.AddAddressRequest;
import kuit.baemin.service.AddressService;
import kuit.baemin.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/addresses")
    public BaseResponse<Addresses> addAddress(@RequestBody @Valid AddAddressRequest addAddressRequest) {
        Addresses addresses = addressService.addAddress(addAddressRequest);
        return new BaseResponse<>(addresses);
    }
}
