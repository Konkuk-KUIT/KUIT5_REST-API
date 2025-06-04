package kuit.baemin.service;
import kuit.baemin.domain.Address;
import kuit.baemin.domain.User;
import kuit.baemin.dto.AddressDto;
import kuit.baemin.dto.SignupRequest;
import kuit.baemin.repository.AddressRepository;
import kuit.baemin.repository.AddressRepositoryV1;
import kuit.baemin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepositoryV1 addressRepository;

    @Transactional
    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    @Transactional
    public Address find(Long addresId){
        return addressRepository.findById(addresId);
    }

    @Transactional
    public Address save(AddressDto addressDto) {
        return addressRepository.save(new Address(addressDto.getUserId(), addressDto.getStatus(),addressDto.getStatus()));
    }

}
