package kuit.baemin.repository.Order;

import kuit.baemin.domain.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderItemRepository {
    Long save(Long orderId, Long menuId, int quantity, BigDecimal itemPrice);
    List<OrderItem> findAllByOrderId(Long orderId);
}
