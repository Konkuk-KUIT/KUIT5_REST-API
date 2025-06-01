package kuit.baemin.repository;

import kuit.baemin.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {
    Store save(Store store);

    List<Store> findStoresByCategoryWithPaging(Long categoryId, int size, Long lastStoreId);
}
