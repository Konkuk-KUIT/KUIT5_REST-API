package kuit.baemin.repository;

import kuit.baemin.domain.store.Store;

import java.util.Optional;

public interface StoreRepository {
    Store save(Store store);
    Optional<Store> findById(Long id);
}
