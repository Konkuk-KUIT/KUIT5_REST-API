package kuit.baemin.controller;

import kuit.baemin.dto.OrderDetailResponse;
import kuit.baemin.dto.OrderRequest;
import kuit.baemin.dto.OrderResponse;
import kuit.baemin.service.OrderService;
import kuit.baemin.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public BaseResponse<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        return new BaseResponse<>(orderService.createOrder(request));
    }

    @GetMapping("/{orderId}")
    public BaseResponse<OrderDetailResponse> getOrderDetail(@PathVariable("orderId") Long orderId) {
        OrderDetailResponse response = orderService.getOrderDetail(orderId);
        return new BaseResponse<>(response);
    }
}
