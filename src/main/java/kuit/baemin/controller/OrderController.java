package kuit.baemin.controller;

import kuit.baemin.domain.Order;
import kuit.baemin.dto.OrderRequest;
import kuit.baemin.service.OrderService;
import kuit.baemin.utils.BaseResponse;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/users/{userId}/orders")
    public BaseResponse<Order> createOrder(
            @PathVariable Long userId,
            @RequestBody @Validated OrderRequest request
    ) {
        Order order = orderService.save(userId, request);
        return new BaseResponse<>(BaseResponseStatus.SUCCESS);
    }

    @GetMapping("/users/{userId}/orders")
    public BaseResponse<List<Order>> getUserOrders(
            @PathVariable Long userId,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false) Long lastOrderId
    ) {
        List<Order> orders = orderService.findOrdersByUserIdWithPaging(userId, size, lastOrderId);
        return new BaseResponse<>( orders);
    }

}
