package kuit.baemin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderItemRequest {
    private Long menuId;
    private int quantity;
    private List<Long> optionIds;
}
