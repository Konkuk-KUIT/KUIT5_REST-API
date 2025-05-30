package kuit.baemin.service;

import kuit.baemin.domain.store.Store;
import kuit.baemin.dto.request.GenerateStoreRequest;
import kuit.baemin.exception.BusinessException;
import kuit.baemin.repository.StoreRepository;
import kuit.baemin.repository.UserRepository;
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

    @Transactional
    public Store save(Long userId, GenerateStoreRequest request){
        userRepository.findById(userId).orElseThrow(() -> new BusinessException(BaseResponseStatus.USER_NOT_FOUND));
        return storeRepository.save(new Store(
                userId,
                1L, // todo : addressId도 추후 추가하기.
                request.getName(),
                request.getMinimumOrderPrice(),
                request.getDeliveryFee()
        ));
    }
}
