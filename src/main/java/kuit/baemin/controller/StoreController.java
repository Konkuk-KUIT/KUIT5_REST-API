package kuit.baemin.controller;

import kuit.baemin.domain.store.Store;
import kuit.baemin.dto.request.GenerateStoreRequest;
import kuit.baemin.service.StoreService;
import kuit.baemin.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    // todo : 오직 유저에 있는 아이디만 가게 개설 가능.
    @PostMapping("/stores")
    public BaseResponse<Store> saveStore(@Validated @RequestBody GenerateStoreRequest request){
        Long userId = 23L; //TODO : 현재 로그인된 사용자 정보에서 받아오기
        log.info("save store request - name : {}, minimum_order_price : {}, delivery_fee : {}", request.getName(), request.getMinimumOrderPrice(), request.getDeliveryFee());
        Store store = storeService.save(userId, request);
        return new BaseResponse<>(store);
    }
}
