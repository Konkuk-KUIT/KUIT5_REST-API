package kuit.baemin.controller;

import jakarta.validation.Valid;
import kuit.baemin.domain.Store;
import kuit.baemin.dto.StoreRequest;
import kuit.baemin.service.StoreService;
import kuit.baemin.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/stores")
    public BaseResponse<Store> createStore(@Valid @RequestBody StoreRequest storeRequest) {
        Store store = storeService.save(storeRequest);
        return new BaseResponse<>(store);
    }

    @GetMapping("/stores")
    public BaseResponse<List<Store>> getStores(
            @RequestParam Long categoryId,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false) Long lastStoreId
    ) {
        List<Store> stores = storeService.findStoresByCategoryWithPaging(categoryId, size, lastStoreId);
        return new BaseResponse<>(stores);
    }

}
