package kuit.baemin.domain.address;

import kuit.baemin.domain.store.Store;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StoreAddress {
    public StoreAddress(Long storeId, Long addressId) {
        this.storeId = storeId;
        this.addressId = addressId;
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    private Long storeAddressId;
    private Long storeId;
    private Long addressId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    private Addresses address;
    private Store store;
}
