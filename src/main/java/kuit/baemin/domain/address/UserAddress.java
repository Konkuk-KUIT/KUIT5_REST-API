package kuit.baemin.domain.address;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserAddress {
    public UserAddress(Long userId, Long addressId, UserAddressType addressType) {
        this.userId = userId;
        this.addressId = addressId;
        this.addressType = addressType;
        this.status = AddressStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private Long userAddressId;
    private Long userId;
    private Long addressId;
    private UserAddressType addressType;
    private AddressStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
