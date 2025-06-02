package kuit.baemin.controller;


import kuit.baemin.domain.User;
import kuit.baemin.dto.RequestDTO.AddressRequestDTO;
import kuit.baemin.dto.ResponseDTO.AddressResponseDTO;
import kuit.baemin.dto.ResponseDTO.UserResponseDTO;
import kuit.baemin.dto.SignupRequest;
import kuit.baemin.service.AddressService;
import kuit.baemin.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
//@Controller
@RestController
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/addresses")
    @ResponseBody
    public BaseResponse<String> postAddress (@Validated @RequestBody AddressRequestDTO addressRequestDTO) {
        log.info(addressRequestDTO.toString());
        String result = addressService.save(addressRequestDTO);
        return new BaseResponse<>(result);
    }
    @GetMapping("/addresses/{id}")
    public BaseResponse<List<AddressResponseDTO>> getAddressById(@PathVariable("id") String userId) {
        List<AddressResponseDTO> addressAll = addressService.findAll(userId);
        return new BaseResponse<>(addressAll);
    }

}
