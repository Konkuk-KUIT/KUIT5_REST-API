package kuit.baemin.service;

import kuit.baemin.domain.Store;
import kuit.baemin.dto.StoreRequest;
import kuit.baemin.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;

    @Transactional
    public Store save(StoreRequest request) {
        return storeRepository.save(new Store(
                null,
                request.getStoreName(),
                request.getLocation(),
                request.getMinimumPrice(),
                request.getActivate(),
                request.getCategoryId()
        ));
    }

    @Transactional(readOnly = true)
    public Store findById(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 가게입니다."));
    }

    @Transactional(readOnly = true)
    public List<Store> findStoresByCategoryWithPaging(Long categoryId, int size, Long lastStoreId) {
        return storeRepository.findStoresByCategoryWithPaging(categoryId, size, lastStoreId);
    }

}
