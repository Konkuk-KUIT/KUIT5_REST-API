package kuit.baemin.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

    private final JdbcTemplate jdbcTemplate;

    public void insertFavorite(Long userId, Long restaurantId) {
        String sql = """
            INSERT INTO favorite_restaurants (user_id, restaurant_id, created_at, updated_at)
            VALUES (?, ?, NOW(), NOW())
        """;
        jdbcTemplate.update(sql, userId, restaurantId);
    }

    public void deleteFavorite(Long userId, Long restaurantId) {
        String sql = """
            DELETE FROM favorite_restaurants
            WHERE user_id = ? AND restaurant_id = ?
        """;
        jdbcTemplate.update(sql, userId, restaurantId);
    }
}

