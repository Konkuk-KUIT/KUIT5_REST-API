package kuit.baemin.repository;

import kuit.baemin.dto.response.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<MenuResponse> menuResponseRowMapper = (rs, rowNum) -> {
        MenuResponse menuResponse = new MenuResponse();
        menuResponse.setId(rs.getLong("id"));
        menuResponse.setRestaurantId(rs.getLong("restaurant_id"));
        menuResponse.setName(rs.getString("name"));
        menuResponse.setPrice(rs.getInt("price"));
        return menuResponse;
    };

    public List<MenuResponse> findMenusByRestaurantId(Long restaurantId) {
        String sql = "SELECT id, restaurant_id, name, price FROM menu WHERE restaurant_id = ?";
        return jdbcTemplate.query(sql, menuResponseRowMapper, restaurantId);
    }
}
