package kuit.baemin.controller;

import jakarta.validation.Valid;
import kuit.baemin.domain.Item;
import kuit.baemin.dto.ItemRequest;
import kuit.baemin.service.ItemService;
import kuit.baemin.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/stores/{storeId}/items")
    public BaseResponse<Item> createItem(@PathVariable Long storeId,
                                         @Valid @RequestBody ItemRequest request) {
        Item item = itemService.save(storeId, request);
        return new BaseResponse<>(item);
    }

    @GetMapping("/stores/{storeId}/items")
    public BaseResponse<List<Item>> getItems(
            @PathVariable Long storeId,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false) Long lastItemId
    ) {
        List<Item> items = itemService.findItemsByStoreWithPaging(storeId, size, lastItemId);
        return new BaseResponse<>(items);
    }

}
