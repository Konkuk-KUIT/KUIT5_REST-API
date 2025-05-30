package kuit.baemin.service;

import kuit.baemin.domain.Order;
import kuit.baemin.domain.Restaurant;
import kuit.baemin.dto.UserOrderResponse;
import kuit.baemin.repository.Order.OrderRepositoryV1;
import kuit.baemin.repository.Restaurant.RestaurantRepositoryV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserOrderService {

    private final OrderRepositoryV1 orderRepository;
    private final RestaurantRepositoryV1 restaurantRepository;

    public List<UserOrderResponse> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);

        return orders.stream().map(order -> {
            Restaurant restaurant = restaurantRepository.findById(order.getRestaurantId())
                    .orElseThrow(() -> new IllegalArgumentException("음식점이 존재하지 않습니다."));

            UserOrderResponse res = new UserOrderResponse();
            res.setOrderId(order.getId());
            res.setRestaurantName(restaurant.getName());
            res.setStatus(order.getStatus().name().toLowerCase());
            res.setTotalPrice(order.getTotalPrice());
            res.setOrderedAt(order.getCreatedAt());
            return res;
        }).collect(Collectors.toList());
    }
}
