package kuit.baemin.repository;

import kuit.baemin.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    Optional<Menu> findByIdAndRestaurantId(Long menuId, Long restaurantId);
}
