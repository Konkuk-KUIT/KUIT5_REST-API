package kuit.baemin.service;

import kuit.baemin.domain.Address;
import kuit.baemin.domain.Restaurant;
import kuit.baemin.dto.AddressDto;
import kuit.baemin.repository.RestaurantRepositoryV1;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    RestaurantRepositoryV1 restaurantRepositoryV1;

    @Transactional
    public List<Restaurant> findAll(){
        return restaurantRepositoryV1.findAll();
    }

    @Transactional
    public Restaurant find(Long restaurantId){
        return restaurantRepositoryV1.findById(restaurantId);
    }

    @Transactional
    public Address save(RestaurantDto restaurantDto) {
        return addressRepository.save(new Address(addressDto.getUserId(), addressDto.getStatus(),addressDto.getStatus()));
    }

    private static void validateEmail() {
        log.error("이메일 검증 오류");
        throw new RuntimeException();
    }

}
