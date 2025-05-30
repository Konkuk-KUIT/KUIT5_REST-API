package kuit.baemin.domain.store;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Store {
    public Store(Long userId, Long addressId, String name, int minimumOrderPrice, int deliveryFee) {
        this.userId = userId;
        this.addressId = addressId;
        this.name = name;
        this.minimumOrderPrice = minimumOrderPrice;
        this.deliveryFee = deliveryFee;
        this.status = StoreStatus.OPEN;
    }

    private Long storeId;
    private Long userId;
    private Long addressId;
    private String name;
    private int minimumOrderPrice;
    private int deliveryFee;
    private StoreStatus status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}