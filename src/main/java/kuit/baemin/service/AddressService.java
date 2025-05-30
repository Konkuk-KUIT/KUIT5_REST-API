package kuit.baemin.service;

import kuit.baemin.domain.address.Addresses;
import kuit.baemin.dto.request.AddAddressRequest;
import kuit.baemin.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {
    private final AddressRepository addressRepository;

    @Transactional
    public Addresses addAddress(AddAddressRequest request){
        return addressRepository.save(new Addresses(request.getCity(), request.getDistrict(), request.getStreetAddress(), request.getAptNumber()));
    }
}
