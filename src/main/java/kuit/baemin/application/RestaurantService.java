package kuit.baemin.application;

import kuit.baemin.domain.Restaurant;
import kuit.baemin.dto.request.RestaurantRequest;
import kuit.baemin.dto.response.RestaurantResponse;
import kuit.baemin.repository.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    public RestaurantResponse getRestaurantDetail(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("해당 식당이 존재하지 않습니다."));

        return RestaurantResponse.from(restaurant);

    }

    @Transactional
    public RestaurantResponse registerRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = Restaurant.builder()
                .name(restaurantRequest.getName())
                .deliveryFee(restaurantRequest.getDeliveryFee())
                .minOrderPrice(restaurantRequest.getMinOrderPrice())
                .longitude(restaurantRequest.getLongitude())
                .latitude(restaurantRequest.getLatitude())
                .phoneNumber(restaurantRequest.getPhoneNumber())
                .build();

        restaurantRepository.save(restaurant);

        return RestaurantResponse.from(restaurant);
    }
}
