package kuit.baemin.service;

import kuit.baemin.domain.Order;
import kuit.baemin.dto.OrderRequest;
import kuit.baemin.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Order save(Long userId, OrderRequest request) {

        Order order = new Order(
                userId,
                request.getStoreId(),
                request.getOrderItems()
        );

        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public List<Order> findOrdersByUserIdWithPaging(Long userId, int size, Long lastOrderId) {
        return orderRepository.findOrdersByUserIdWithPaging(userId, size, lastOrderId);
    }

}
