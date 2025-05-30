package kuit.baemin.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;

// 누가 만들고, 가게 이름은 무엇이고, 최저 주문 금액, 배달 비용
// todo : 로그인 사용자 정보로 대체 하고, address 바인드하기
@Getter
public class GenerateStoreRequest {
    @JsonCreator
    public GenerateStoreRequest(@JsonProperty("name") String name,
                                @JsonProperty("minimum_order_price") int minimumOrderPrice,
                                @JsonProperty("delivery_fee") int deliveryFee) {
        this.name = name;
        this.minimumOrderPrice = minimumOrderPrice;
        this.deliveryFee = deliveryFee;
    }

    @Size(max = 30)
    private String name;
    @Min(0)
    @Max(50000)
    private int minimumOrderPrice;
    @Min(0)
    @Max(5000)
    private int deliveryFee;
}
