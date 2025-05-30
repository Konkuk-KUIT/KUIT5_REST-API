package kuit.baemin.controller;

import kuit.baemin.dto.request.OrderRequest;
import kuit.baemin.service.OrderService;
import kuit.baemin.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public BaseResponse<String> createOrder(@RequestBody OrderRequest request) {
        orderService.createOrder(request);
        return new BaseResponse<>("Order created successfully");
    }
}

