package kuit.baemin.service;

import kuit.baemin.dto.request.UserAddressRequest;
import kuit.baemin.dto.response.UserAddressResponse;
import kuit.baemin.repository.UserAddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserAddressService {
    private final UserAddressRepository repository;

    @Transactional
    public UserAddressResponse save(UserAddressRequest request, Long userId) {
        Long newId = repository.save(request, userId);
        return repository.findById(newId);
    }

}
