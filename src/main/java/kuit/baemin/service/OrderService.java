package kuit.baemin.service;

import kuit.baemin.domain.Menu;
import kuit.baemin.domain.MenuOption;
import kuit.baemin.domain.Order;
import kuit.baemin.domain.OrderItem;
import kuit.baemin.dto.OrderDetailResponse;
import kuit.baemin.dto.OrderItemRequest;
import kuit.baemin.dto.OrderRequest;
import kuit.baemin.dto.OrderResponse;
import kuit.baemin.repository.Menu.MenuOptionRepositoryV1;
import kuit.baemin.repository.Menu.MenuRepositoryV1;
import kuit.baemin.repository.Order.OrderItemOptionRepositoryV1;
import kuit.baemin.repository.Order.OrderItemRepositoryV1;
import kuit.baemin.repository.Order.OrderRepositoryV1;
import kuit.baemin.repository.Order.OrderStatusLogRepositoryV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepositoryV1 orderRepository;
    private final OrderItemRepositoryV1 orderItemRepository;
    private final OrderItemOptionRepositoryV1 orderItemOptionRepository;
    private final OrderStatusLogRepositoryV1 orderStatusLogRepository;
    private final MenuRepositoryV1 menuRepository;
    private final MenuOptionRepositoryV1 menuOptionRepository;

    public OrderResponse createOrder(OrderRequest request) {
        BigDecimal totalPrice = BigDecimal.ZERO;

        // 가격 계산
        for (OrderItemRequest item : request.getItems()) {
            Menu menu = menuRepository.findById(item.getMenuId())
                    .orElseThrow(() -> new IllegalArgumentException("메뉴가 존재하지 않습니다."));
            BigDecimal itemTotal = menu.getPrice();

            for (Long optionId : item.getOptionIds()) {
                MenuOption option = menuOptionRepository.findById(optionId)
                        .orElseThrow(() -> new IllegalArgumentException("옵션이 존재하지 않습니다."));
                itemTotal = itemTotal.add(option.getExtraPrice());
            }

            totalPrice = totalPrice.add(itemTotal.multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        // 주문 저장
        Long orderId = orderRepository.save(
                request.getUserId(), request.getAddressId(), request.getRestaurantId(), totalPrice);

        // 아이템 및 옵션 저장
        for (OrderItemRequest item : request.getItems()) {
            Menu menu = menuRepository.findById(item.getMenuId()).get();
            Long orderItemId = orderItemRepository.save(orderId, item.getMenuId(), item.getQuantity(), menu.getPrice());

            for (Long optionId : item.getOptionIds()) {
                orderItemOptionRepository.save(orderItemId, optionId);
            }
        }

        // 상태 로그
        orderStatusLogRepository.save(orderId, Order.Status.ORDERED);

        return new OrderResponse(orderId);
    }

    public OrderDetailResponse getOrderDetail(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 존재하지 않습니다."));

        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(orderId);

        List<OrderDetailResponse.OrderItemDetail> itemDetails = new ArrayList<>();

        for (OrderItem item : orderItems) {
            Menu menu = menuRepository.findById(item.getMenuId()).orElseThrow();

            OrderDetailResponse.OrderItemDetail detail = new OrderDetailResponse.OrderItemDetail();
            detail.setMenuName(menu.getName());
            detail.setQuantity(item.getQuantity());
            detail.setItemPrice(item.getItemPrice());

            List<Long> optionIds = orderItemOptionRepository.findOptionIdsByOrderItemId(item.getId());
            List<MenuOption> options = menuOptionRepository.findAllByIds(optionIds);

            List<OrderDetailResponse.OptionDetail> optionDetails = options.stream().map(o -> {
                OrderDetailResponse.OptionDetail opt = new OrderDetailResponse.OptionDetail();
                opt.setName(o.getName());
                opt.setExtraPrice(o.getExtraPrice());
                return opt;
            }).collect(Collectors.toList());

            detail.setOptions(optionDetails);
            itemDetails.add(detail);
        }

        OrderDetailResponse response = new OrderDetailResponse();
        response.setOrderId(orderId);
        response.setStatus(order.getStatus().name().toLowerCase());
        response.setItems(itemDetails);

        return response;
    }

}
