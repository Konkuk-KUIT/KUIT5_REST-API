package kuit.baemin.service;

import kuit.baemin.domain.Item;
import kuit.baemin.dto.ItemRequest;
import kuit.baemin.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item save(Long storeId, ItemRequest request) {
        return itemRepository.save(new Item(
                null,
                storeId,
                request.getCategoryId(),
                request.getItemTitle(),
                request.getItemPrice(),
                request.getItemPicture(),
                request.getItemExplanation()
        ));
    }

    @Transactional(readOnly = true)
    public List<Item> findItemsByStoreWithPaging(Long storeId, int size, Long lastItemId) {
        return itemRepository.findItemsByStoreWithPaging(storeId, size, lastItemId);
    }


}
