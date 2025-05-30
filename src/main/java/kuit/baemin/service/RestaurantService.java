package kuit.baemin.service;

import kuit.baemin.domain.Menu;
import kuit.baemin.domain.Restaurant;
import kuit.baemin.dto.RestaurantDetailResponse;
import kuit.baemin.repository.Menu.MenuRepository;
import kuit.baemin.repository.Restaurant.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    public RestaurantDetailResponse getRestaurantDetail(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("음식점이 존재하지 않습니다."));

        List<Menu> menus = menuRepository.findAllByRestaurantId(restaurantId);

        return RestaurantDetailResponse.of(restaurant, menus);
    }
}