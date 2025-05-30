package kuit.baemin.controller;

import kuit.baemin.dto.RestaurantDetailResponse;
import kuit.baemin.service.RestaurantService;
import kuit.baemin.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/{restaurantId}")
    public BaseResponse<RestaurantDetailResponse> getRestaurantDetail(@PathVariable("restaurantId") Long restaurantId) {
        RestaurantDetailResponse response = restaurantService.getRestaurantDetail(restaurantId);
        return new BaseResponse<>(response);
    }
}