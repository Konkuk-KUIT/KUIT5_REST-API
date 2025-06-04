package kuit.baemin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class RestaurantDto {
    @JsonProperty
    private String name;
    @JsonProperty
    private String category;
    @JsonProperty
    private String address;
    @JsonProperty
    private String pictureUrl;
    @JsonProperty
    private String phone;
    @JsonProperty
    private String content;
    @JsonProperty
    private Integer minDeliveryPrice;
    private Timestamp createdDate;
    private Timestamp modifiedDate;

}
