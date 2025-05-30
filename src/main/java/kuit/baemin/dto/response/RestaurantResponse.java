package kuit.baemin.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponse {
    private Long id;
    private String name;
    private Double latitude;
    private Double longitude;
    private Integer min_order_price;
    private Integer delivery_fee;
}
