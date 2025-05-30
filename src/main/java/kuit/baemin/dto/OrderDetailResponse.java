package kuit.baemin.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderDetailResponse {
    private Long orderId;
    private String status;
    private List<OrderItemDetail> items;

    @Getter
    @Setter
    public static class OrderItemDetail {
        private String menuName;
        private int quantity;
        private BigDecimal itemPrice;
        private List<OptionDetail> options;
    }

    @Getter
    @Setter
    public static class OptionDetail {
        private String name;
        private BigDecimal extraPrice;
    }
}