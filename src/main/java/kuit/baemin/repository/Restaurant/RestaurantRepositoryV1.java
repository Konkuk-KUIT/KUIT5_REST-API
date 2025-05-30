package kuit.baemin.repository.Restaurant;

import kuit.baemin.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryV1 implements RestaurantRepository{

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Restaurant> findById(Long restaurantId) {
        String sql = "SELECT * FROM restaurant WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{restaurantId}, (rs, rowNum) -> {
            Restaurant restaurant = new Restaurant();
            restaurant.setId(rs.getLong("id"));
            restaurant.setName(rs.getString("name"));
            restaurant.setCategory(rs.getString("category"));
            restaurant.setRoadAddress(rs.getString("road_address"));
            restaurant.setLatitude(rs.getDouble("latitude"));
            restaurant.setLongitude(rs.getDouble("longitude"));
            restaurant.setMinimumOrderPrice(rs.getBigDecimal("minimum_order_price"));
            restaurant.setStatus(Restaurant.Status.valueOf(rs.getString("status").toUpperCase()));
            restaurant.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            restaurant.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return restaurant;
        }).stream().findFirst();
    }
}
