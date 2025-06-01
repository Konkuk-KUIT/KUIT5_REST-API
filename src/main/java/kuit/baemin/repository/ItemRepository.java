package kuit.baemin.repository;

import kuit.baemin.domain.Item;

import java.util.List;

public interface ItemRepository {
    Item save(Item item);

    List<Item> findItemsByStoreWithPaging(Long storeId, int size, Long lastItemId);

}
