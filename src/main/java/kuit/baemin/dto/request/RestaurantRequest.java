package kuit.baemin.dto.request;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequest {
    private String name;
    private String phoneNumber;
    private int minOrderPrice;
    private int deliveryFee;
    private double latitude;
    private double longitude;
}
