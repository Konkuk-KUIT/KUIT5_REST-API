package kuit.baemin.service;


import kuit.baemin.domain.User;
import kuit.baemin.dto.RequestDTO.AddressRequestDTO;
import kuit.baemin.dto.ResponseDTO.AddressResponseDTO;
import kuit.baemin.dto.SignupRequest;
import kuit.baemin.repository.AddressRepository;
import kuit.baemin.repository.UserRepository;
import kuit.baemin.repository.UserRepositoryV6;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class AddressService {
    private final AddressRepository addressRepository;
    private final UserRepositoryV6 userRepository;

    @Transactional
    public String save(AddressRequestDTO addressRequestDTO) {
        if(userRepository.hasUserId(addressRequestDTO.getUserId())){
            return addressRepository.save(addressRequestDTO);
        }
        throw new IllegalArgumentException("존재하지 않는 사용자 ID입니다.");
    }


    public List<AddressResponseDTO> findAll(String userId) {
        if(userRepository.hasUserId(userId)){
            return addressRepository.getAddresses(userId);
        }
        throw new IllegalArgumentException("존재하지 않는 사용자 ID입니다.");
    }
}
