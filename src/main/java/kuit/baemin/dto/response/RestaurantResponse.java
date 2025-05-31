package kuit.baemin.dto.response;

import kuit.baemin.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponse {
    private String name;
    private String phoneNumber;
    private String createdAt;
    private int minOrderPrice;
    private int deliveryFee;
    private double latitude;
    private double longitude;

    public static RestaurantResponse from(Restaurant restaurant) {
        return RestaurantResponse.builder()
                .name(restaurant.getName())
                .phoneNumber(restaurant.getPhoneNumber())
                .deliveryFee(restaurant.getDeliveryFee())
                .createdAt(restaurant.getCreatedAt())
                .minOrderPrice(restaurant.getMinOrderPrice())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .build();
    }
}
