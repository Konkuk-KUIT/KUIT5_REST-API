package kuit.baemin.repository.Restaurant;

import kuit.baemin.domain.Restaurant;

import java.util.Optional;

public interface RestaurantRepository {
    Optional<Restaurant> findById(Long restaurantId);
}