package kuit.baemin.controller;

import kuit.baemin.dto.MenuDetailResponse;
import kuit.baemin.service.MenuService;
import kuit.baemin.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/menus")
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/{menuId}")
    public BaseResponse<MenuDetailResponse> getMenuDetail(@PathVariable("menuId") Long menuId) {
        MenuDetailResponse response = menuService.getMenuDetail(menuId);
        return new BaseResponse<>(response);
    }
}
