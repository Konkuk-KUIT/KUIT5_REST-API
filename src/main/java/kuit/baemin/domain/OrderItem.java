package kuit.baemin.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class OrderItem {

    private Long id;

    private Long orderId;

    private Long menuId;

    private int quantity;

    private BigDecimal itemPrice;
}
