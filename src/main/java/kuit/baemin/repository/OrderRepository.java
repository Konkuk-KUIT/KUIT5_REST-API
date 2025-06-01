package kuit.baemin.repository;


import kuit.baemin.domain.Order;

import java.util.List;

public interface OrderRepository {
    Order save(Order order);

    List<Order> findOrdersByUserIdWithPaging(Long userId, int size, Long lastOrderId);
}
