package kuit.baemin.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AddAddressRequest {
    @JsonCreator
    public AddAddressRequest(@JsonProperty("city") String city,
                             @JsonProperty("district") String district,
                             @JsonProperty("street_address") String streetAddress,
                             @JsonProperty("apt_number") String aptNumber) {
        this.city = city;
        this.district = district;
        this.streetAddress = streetAddress;
        this.aptNumber = aptNumber;
    }

    @NotBlank
    private String city;
    @NotBlank
    private String district;
    @NotBlank
    private String streetAddress;
    private String aptNumber;
}
