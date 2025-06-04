package kuit.baemin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class AddressDto {
    private Long addressId;
    @JsonProperty
    private Long userId;
    @JsonProperty
    private String address;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
    @JsonProperty
    private String status;

}
