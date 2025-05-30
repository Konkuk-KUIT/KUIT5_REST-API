package kuit.baemin.dto.request;

import kuit.baemin.domain.address.UserAddressType;
import lombok.Getter;

@Getter
public class AddUserAddressRequest {
    private Long addressId;
    private UserAddressType addressType;
}
