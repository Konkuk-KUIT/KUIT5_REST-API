package kuit.baemin.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;

// 누가 만들고, 가게 이름은 무엇이고, 최저 주문 금액, 배달 비용
@Getter
public class GenerateStoreRequest {
    @JsonCreator
    public GenerateStoreRequest(@JsonProperty("name") String name,
                                @JsonProperty("minimum_order_price") int minimumOrderPrice,
                                @JsonProperty("delivery_fee") int deliveryFee,
                                @JsonProperty("address_id") Long addressId) {
        this.name = name;
        this.minimumOrderPrice = minimumOrderPrice;
        this.deliveryFee = deliveryFee;
        this.addressId = addressId;
    }

    @Size(max = 30)
    private String name;
    @Min(0)
    @Max(50000)
    private int minimumOrderPrice;
    @Min(0)
    @Max(5000)
    private int deliveryFee;
    @NotNull
    private Long addressId;
}
