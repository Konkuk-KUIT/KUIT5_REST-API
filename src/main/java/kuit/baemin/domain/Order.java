package kuit.baemin.domain;

import jakarta.validation.constraints.NotNull;
import kuit.baemin.dto.OrderItemRequest;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class Order {

    private Long orderId;
    private Long userId;
    private Long storeId;
    private List<OrderItemRequest> orderItems;
    private String orderStatus;
    private LocalDateTime orderDatetime;

    public Order(Long orderId, Long userId, Long storeId, String orderStatus, LocalDateTime orderDatetime) {
        this.orderId = orderId;
        this.userId = userId;
        this.storeId = storeId;
        this.orderStatus = orderStatus;
        this.orderDatetime = orderDatetime;
    }


    public Order(Long userId, Long storeId, List<OrderItemRequest> orderItems) {
        this.userId = userId;
        this.storeId = storeId;
        this.orderItems = orderItems;
    }


}