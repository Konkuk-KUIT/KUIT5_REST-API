package kuit.baemin.dto.request.address;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import kuit.baemin.domain.address.UserAddressType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddUserAddressRequest {
    @JsonCreator
    public AddUserAddressRequest(@JsonProperty("address_id") Long addressId,
                                 @JsonProperty("address_type") UserAddressType addressType) {
        this.addressId = addressId;
        this.addressType = addressType;
    }

    @NotNull
    private Long addressId;
    @NotNull
    private UserAddressType addressType;
}
