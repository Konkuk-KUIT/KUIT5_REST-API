package kuit.baemin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class Restaurant {
    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private int minOrderPrice;
    private int deliveryFee;
    private String status;
    private LocalDate created_at;
    private LocalDate updated_at;
}

