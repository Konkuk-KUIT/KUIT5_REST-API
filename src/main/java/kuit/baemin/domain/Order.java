package kuit.baemin.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Order {

    private Long id;

    private Long userId;

    private Long addressId;

    private Long restaurantId;

    private BigDecimal totalPrice;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum Status {
        ORDERED, PREPARING, DELIVERING, COMPLETED, CANCELED
    }
}