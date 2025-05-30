package kuit.baemin.repository.Order;

import kuit.baemin.domain.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    Long save(Long userId, Long addressId, Long restaurantId, BigDecimal totalPrice);
    Optional<Order> findById(Long orderId);
    List<Order> findAllByUserId(Long userId);
}