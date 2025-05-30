package kuit.baemin.repository;

import kuit.baemin.dto.response.MenuResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    public Optional<MenuResponse> findById(Long menuId) {
        String sql = """
            SELECT id, restaurant_id, name, description, price, status, created_at, updated_at
            FROM menu
            WHERE id = ?
        """;

        List<MenuResponse> results = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MenuResponse.class), menuId);
        return results.stream().findFirst();
    }
}
