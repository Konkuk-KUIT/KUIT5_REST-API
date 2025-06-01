package kuit.baemin.controller;

import kuit.baemin.domain.store.Store;
import kuit.baemin.dto.request.GenerateStoreRequest;
import kuit.baemin.service.StoreService;
import kuit.baemin.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/stores")
    public BaseResponse<Store> saveStore(@Validated @RequestBody GenerateStoreRequest request,
                                         @RequestAttribute Long userId){
        log.info("save store request - name : {}, minimum_order_price : {}, delivery_fee : {}", request.getName(), request.getMinimumOrderPrice(), request.getDeliveryFee());
        Store store = storeService.save(userId, request);
        return new BaseResponse<>(store);
    }
}
