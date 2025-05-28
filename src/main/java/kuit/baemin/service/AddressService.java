package kuit.baemin.service;

import kuit.baemin.dto.AddressRequest;
import kuit.baemin.dto.UserAddressResponse;
import kuit.baemin.repository.Address.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public Long addAddress(Long userId, AddressRequest request) {
        return addressRepository.saveAddress(userId, request);
    }

    public List<UserAddressResponse> getUserAddresses(Long userId) {
        return addressRepository.findAllByUserId(userId);
    }
}
