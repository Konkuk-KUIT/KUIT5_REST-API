package kuit.baemin.dto;

import kuit.baemin.domain.Menu;
import kuit.baemin.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class RestaurantDetailResponse {

    private Long restaurantId;
    private String name;
    private String category;
    private String roadAddress;
    private List<MenuSimpleResponse> menus;

    public static RestaurantDetailResponse of(Restaurant restaurant, List<Menu> menus) {
        return new RestaurantDetailResponse(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getCategory(),
                restaurant.getRoadAddress(),
                menus.stream()
                        .map(MenuSimpleResponse::from)
                        .collect(Collectors.toList())
        );
    }
}