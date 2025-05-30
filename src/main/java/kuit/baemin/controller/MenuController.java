package kuit.baemin.controller;

import kuit.baemin.dto.response.MenuResponse;
import kuit.baemin.service.MenuService;
import kuit.baemin.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/restaurants/{restaurant_id}/menus")
    public BaseResponse<List<MenuResponse>> getMenus(@PathVariable("restaurant_id") Long restaurantId) {
        List<MenuResponse> menus = menuService.getMenusByRestaurantId(restaurantId);
        return new BaseResponse<>(menus);
    }

    @GetMapping("/menus/{menuId}")
    public BaseResponse<Optional<MenuResponse>> getMenuById(@PathVariable Long menuId) {
        Optional<MenuResponse> menu = menuService.getMenuById(menuId);
        return new BaseResponse<>(menu);
    }
}
