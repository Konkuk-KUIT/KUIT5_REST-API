package kuit.baemin.controller;

import jakarta.validation.Valid;
import kuit.baemin.domain.Address;
import kuit.baemin.domain.Restaurant;
import kuit.baemin.dto.AddressDto;
import kuit.baemin.repository.RestaurantRepositoryV1;
import kuit.baemin.service.AddressService;
import kuit.baemin.service.RestaurantService;
import kuit.baemin.utils.BaseResponse;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/retaurants")
    public BaseResponse<List<Restaurant>> getAddresses(){
        return new BaseResponse<>(restaurantService.findAll());
    }
}

