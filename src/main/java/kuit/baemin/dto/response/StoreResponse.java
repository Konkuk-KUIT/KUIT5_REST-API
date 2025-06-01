package kuit.baemin.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import kuit.baemin.domain.store.StoreStatus;
import lombok.Getter;

@Getter
public class StoreResponse {
    @JsonCreator
    public StoreResponse(@JsonProperty("store_name") String storeName,
                         @JsonProperty("minimum_order_price") int minimumOrderPrice,
                         @JsonProperty("delivery_fee") int deliveryFee,
                         @JsonProperty("status") StoreStatus status) {
        this.storeName = storeName;
        this.minimumOrderPrice = minimumOrderPrice;
        this.deliveryFee = deliveryFee;
        this.status = status;
    }


    private String storeName;
    private int minimumOrderPrice;
    private int deliveryFee;
    private StoreStatus status;
}
