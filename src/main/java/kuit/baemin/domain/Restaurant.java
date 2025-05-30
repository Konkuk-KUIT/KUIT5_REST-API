package kuit.baemin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Restaurant {
    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private int minOrderPrice;
    private int deliveryFee;
}

