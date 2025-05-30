package kuit.baemin.repository;

import kuit.baemin.dto.response.CategoryResponse;
import kuit.baemin.dto.response.RestaurantResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<CategoryResponse> findAllActive() {
        String sql = "SELECT * FROM category WHERE status = 'active'";
        return jdbcTemplate.query(sql, categoryRowMapper());
    }

    private RowMapper<CategoryResponse> categoryRowMapper() {
        return (rs, rowNum) -> {
            CategoryResponse res = new CategoryResponse();
            res.setId(rs.getLong("id"));
            res.setName(rs.getString("name"));
            res.setStatus(rs.getString("status"));
            res.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            res.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return res;
        };
    }

    public List<RestaurantResponse> findRestaurantsByCategoryId(Long categoryId) {
        String sql = """
            SELECT r.id, r.name, r.latitude, r.longitude, r.min_order_price, r.delivery_fee
            FROM restaurant_category rc
            JOIN restaurant r ON rc.restaurant_id = r.id
            WHERE rc.category_id = ?
        """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RestaurantResponse.class), categoryId);
    }
}
