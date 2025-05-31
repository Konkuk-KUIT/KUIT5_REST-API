package kuit.baemin.application;

import jakarta.persistence.EntityNotFoundException;
import kuit.baemin.domain.Menu;
import kuit.baemin.domain.Restaurant;
import kuit.baemin.dto.request.MenuRequest;
import kuit.baemin.dto.response.MenuResponse;
import kuit.baemin.repository.MenuRepository;
import kuit.baemin.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    public MenuResponse getMenuDetail(Long restaurantId, Long menuId) {
        Menu menu = menuRepository.findByIdAndRestaurantId(restaurantId, menuId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 존재하지 않습니다."));

        return MenuResponse.from(menu);
    }

    @Transactional
    public MenuResponse registerMenu(Long restaurantId, MenuRequest menuRequest) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("해당 음식점이 존재하지 않습니다."));

        Menu menu = Menu.builder().
                name(menuRequest.getName())
                .price(menuRequest.getPrice())
                .description(menuRequest.getDescription())
                .imgUrl(menuRequest.getImgUrl())
                .status(menuRequest.getStatus())
                .restaurant(restaurant)
                .build();

        menuRepository.save(menu);

        return MenuResponse.from(menu);
    }

    @Transactional
    public MenuResponse updateMenu(Long restaurantId, Long menuId, MenuRequest menuRequest) {
        Menu menu = menuRepository.findByIdAndRestaurantId(menuId, restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("해당 메뉴가 존재하지 않습니다."));

        menu.update(menuRequest);
        return MenuResponse.from(menu);
    }

    public List<MenuResponse> getMenus(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("해당 음식점이 존재하지 않습니다."));

        List<Menu> menus = menuRepository.findAllByRestaurantId(restaurantId);
        return menus.stream()
                .map(MenuResponse::from)
                .collect(Collectors.toList());}
}
