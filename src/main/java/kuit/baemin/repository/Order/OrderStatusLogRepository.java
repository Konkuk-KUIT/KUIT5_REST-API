package kuit.baemin.repository.Order;

import kuit.baemin.domain.Order;

public interface OrderStatusLogRepository {
    void save(Long orderId, Order.Status status);
}
