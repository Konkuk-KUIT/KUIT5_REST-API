package kuit.baemin.controller;

import jakarta.validation.Valid;
import kuit.baemin.domain.Address;
import kuit.baemin.dto.AddressDto;
import kuit.baemin.service.AddressService;
import kuit.baemin.utils.BaseResponse;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/address")
    public BaseResponse<List<Address>> getAddresses(){
        return new BaseResponse<>(addressService.findAll());
    }

    @GetMapping("/address/{addressId}")
    public BaseResponse<Address> getAddress(@Valid @PathVariable Long addressId){
        return new BaseResponse<>(addressService.find(addressId));
    }

    @PostMapping("/address")
    public BaseResponse<Address> addAddress(@Valid @RequestBody AddressDto addressDto, BindingResult result){
        if (result.hasErrors()) {
            // 에러 응답 처리 (예: 첫 번째 에러 메시지 리턴)
            return new BaseResponse<>(BaseResponseStatus.INVALID_REQUEST);
        }
        System.out.println("addressDto : "+addressDto.getAddressId());
        Address address = addressService.save(addressDto);
        return new BaseResponse<>(address);
    }
}
