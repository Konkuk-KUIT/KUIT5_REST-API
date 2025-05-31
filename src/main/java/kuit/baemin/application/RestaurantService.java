package kuit.baemin.application;

import kuit.baemin.domain.Restaurant;
import kuit.baemin.dto.response.RestaurantResponse;
import kuit.baemin.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    public RestaurantResponse getRestaurantDetail(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("해당 식당이 존재하지 않습니다."));

        return RestaurantResponse.from(restaurant);

    }
}
