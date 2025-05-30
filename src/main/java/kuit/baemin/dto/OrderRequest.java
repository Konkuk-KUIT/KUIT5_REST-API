package kuit.baemin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private Long userId;
    private Long addressId;
    private Long restaurantId;
    private List<OrderItemRequest> items;
}