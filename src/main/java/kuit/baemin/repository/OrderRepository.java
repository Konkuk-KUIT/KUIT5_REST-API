package kuit.baemin.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class OrderRepository{

    private final JdbcTemplate jdbcTemplate;

    public Long insertOrder(Long userId, Long restaurantId) {
        String sql = """
            INSERT INTO `order` (user_id, restaurant_id, status, created_at, updated_at)
            VALUES (?, ?, 'active', NOW(), NOW())
        """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, userId);
            ps.setLong(2, restaurantId);
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public Long insertOrderMenu(Long orderId, Long menuId, int quantity) {
        String sql = """
            INSERT INTO order_menu (order_id, menu_id, quantity, status, created_at, updated_at)
            VALUES (?, ?, ?, 'active', NOW(), NOW())
        """;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, orderId);
            ps.setLong(2, menuId);
            ps.setInt(3, quantity);
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    public void insertOrderMenuOption(Long orderMenuId, Long menuOptionId) {
        String sql = """
            INSERT INTO order_menu_option (order_item_id, menu_option_id, status, created_at, updated_at)
            VALUES (?, ?, 'active', NOW(), NOW())
        """;
        jdbcTemplate.update(sql, orderMenuId, menuOptionId);
    }
}

