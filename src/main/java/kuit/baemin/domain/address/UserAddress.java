package kuit.baemin.domain.address;

import java.time.LocalDateTime;

public class UserAddress {
    private Long userAddressId;
    private Long userId;
    private Long addressId;
    private UserAddressType addressType;
    private AddressStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
