package kuit.baemin.service;

import kuit.baemin.domain.address.Addresses;
import kuit.baemin.domain.address.UserAddress;
import kuit.baemin.domain.user.User;
import kuit.baemin.dto.request.address.AddUserAddressRequest;
import kuit.baemin.exception.BusinessException;
import kuit.baemin.repository.UserRepository;
import kuit.baemin.repository.address.AddressRepository;
import kuit.baemin.repository.address.UserAddressRepository;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserAddressService {
    private final AddressRepository addressRepository;
    private final UserAddressRepository userAddressRepository;
    private final UserRepository userRepository;

    @Transactional
    public void setUserAddress(Long userId, AddUserAddressRequest addUserAddressRequest){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(BaseResponseStatus.USER_NOT_FOUND));
        Addresses addresses = addressRepository.findById(addUserAddressRequest.getAddressId())
                .orElseThrow(() -> new BusinessException(BaseResponseStatus.ADDRESS_NOT_FOUND));

        UserAddress userAddress = new UserAddress(userId, addresses.getAddressId(), addUserAddressRequest.getAddressType());
        userAddressRepository.save(userAddress);
        userRepository.updateUserAddress(userId, userAddress.getUserAddressId());
    }
}
