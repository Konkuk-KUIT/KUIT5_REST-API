package kuit.baemin.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Getter
@Setter
public class Restaurant {
    public Restaurant(String name, String category, String address, String pictureUrl, String phone, String content, Integer minDeliveryPrice){
        this.name = name;
        this.category = category;
        this.address = address;
        this.pictureUrl = pictureUrl;
        this.phone = phone;
        this.content = content;
        this.minDeliveryPrice = minDeliveryPrice;
        this.createdDate = Timestamp.valueOf(LocalDateTime.now());
        this.modifiedDate = Timestamp.valueOf(LocalDateTime.now());
    }

    private Long restaurantId;
    private String name;
    private String category;
    private String address;
    private String pictureUrl;
    private String phone;
    private String content;
    private Integer minDeliveryPrice;
    private Timestamp createdDate;
    private Timestamp modifiedDate;
}
