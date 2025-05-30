package kuit.baemin.service;

import kuit.baemin.dto.request.OrderMenuOptionRequest;
import kuit.baemin.dto.request.OrderMenuRequest;
import kuit.baemin.dto.request.OrderRequest;
import kuit.baemin.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(OrderRequest request) {
        Long orderId = orderRepository.insertOrder(request.getUserId(), request.getRestaurantId());

        for (OrderMenuRequest menu : request.getOrderMenus()) {
            Long orderMenuId = orderRepository.insertOrderMenu(orderId, menu.getMenuId(), menu.getQuantity());

            for (OrderMenuOptionRequest option : menu.getOrderMenuOptions()) {
                orderRepository.insertOrderMenuOption(orderMenuId, option.getMenuOptionId());
            }
        }
    }
}

