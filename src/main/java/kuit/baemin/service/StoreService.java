package kuit.baemin.service;

import kuit.baemin.domain.address.StoreAddress;
import kuit.baemin.domain.store.Store;
import kuit.baemin.dto.request.GenerateStoreRequest;
import kuit.baemin.dto.response.StoreResponse;
import kuit.baemin.exception.BusinessException;
import kuit.baemin.repository.StoreRepository;
import kuit.baemin.repository.UserRepository;
import kuit.baemin.repository.address.StoreAddressRepository;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StoreService {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final StoreAddressRepository storeAddressRepository;

    @Transactional
    public Store save(Long userId, GenerateStoreRequest request){
        userRepository.findById(userId).orElseThrow(() -> new BusinessException(BaseResponseStatus.USER_NOT_FOUND));

        Store store = new Store(
                userId,
                request.getAddressId(),
                request.getName(),
                request.getMinimumOrderPrice(),
                request.getDeliveryFee()
        );
        Store savedStore = storeRepository.save(store);

        //StoreAddress 저장
        StoreAddress storeAddress = new StoreAddress(
                savedStore.getStoreId(),
                request.getAddressId()
        );
        storeAddressRepository.save(storeAddress);


        return savedStore;
    }

    @Transactional
    public StoreResponse findById(Long id){
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new BusinessException(BaseResponseStatus.STORE_NOT_FOUND));
        return new StoreResponse(store.getName(), store.getMinimumOrderPrice(), store.getDeliveryFee(), store.getStatus());
    }
}
