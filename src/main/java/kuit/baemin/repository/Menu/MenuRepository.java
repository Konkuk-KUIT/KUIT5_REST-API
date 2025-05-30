package kuit.baemin.repository.Menu;

import kuit.baemin.domain.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuRepository {
    List<Menu> findAllByRestaurantId(Long restaurantId);
    Optional<Menu> findById(Long menuId);
}