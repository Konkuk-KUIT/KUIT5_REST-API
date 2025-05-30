package kuit.baemin.repository.UserRepository;

import kuit.baemin.domain.User;
import kuit.baemin.dto.response.RestaurantResponse;

import java.util.Collections;
import java.util.List;

public interface UserRepository {

    public User save(User user);

    default List<RestaurantResponse> findFavoriteRestaurantsByUserId(Long userId){
        return Collections.emptyList();
    }
}
