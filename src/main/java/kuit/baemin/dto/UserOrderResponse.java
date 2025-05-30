package kuit.baemin.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class UserOrderResponse {
    private Long orderId;
    private String restaurantName;
    private String status;
    private BigDecimal totalPrice;
    private LocalDateTime orderedAt;
}