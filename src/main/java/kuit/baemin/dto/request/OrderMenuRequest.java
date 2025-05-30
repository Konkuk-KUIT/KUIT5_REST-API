package kuit.baemin.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class OrderMenuRequest {
    private Long menuId;
    private Integer quantity;
    private List<OrderMenuOptionRequest> orderMenuOptions;
}
