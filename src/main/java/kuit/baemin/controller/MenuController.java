package kuit.baemin.controller;

import kuit.baemin.dto.response.MenuResponse;
import kuit.baemin.service.MenuService;
import kuit.baemin.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/restaurants")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/{restaurant_id}/menus")
    public BaseResponse<List<MenuResponse>> getMenus(@PathVariable("restaurant_id") Long restaurantId) {
        List<MenuResponse> menus = menuService.getMenusByRestaurantId(restaurantId);
        return new BaseResponse<>(menus);
    }
}
