package kuit.baemin.repository.Order;

import java.util.List;

public interface OrderItemOptionRepository {
    void save(Long orderItemId, Long menuOptionId);
    List<Long> findOptionIdsByOrderItemId(Long orderItemId);
}
