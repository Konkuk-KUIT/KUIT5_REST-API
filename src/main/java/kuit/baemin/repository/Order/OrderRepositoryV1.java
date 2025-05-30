package kuit.baemin.repository.Order;

import kuit.baemin.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV1 implements OrderRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Long userId, Long addressId, Long restaurantId, BigDecimal totalPrice) {
        String sql = "INSERT INTO `order` (user_id, address_id, restaurant_id, total_price, status, created_at, updated_at) " +
                "VALUES (?, ?, ?, ?, 'ordered', NOW(), NOW())";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, userId);
            ps.setLong(2, addressId);
            ps.setLong(3, restaurantId);
            ps.setBigDecimal(4, totalPrice);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        String sql = "SELECT * FROM `order` WHERE id = ?";
        return jdbcTemplate.query(sql, new Object[]{orderId}, (rs, rowNum) -> {
            Order order = new Order();
            order.setId(rs.getLong("id"));
            order.setUserId(rs.getLong("user_id"));
            order.setAddressId(rs.getLong("address_id"));
            order.setRestaurantId(rs.getLong("restaurant_id"));
            order.setTotalPrice(rs.getBigDecimal("total_price"));
            // toUpperCase로 DB에 소문자로 저장되어있는것을 모두 대문자로 변환해줌
            order.setStatus(Order.Status.valueOf(rs.getString("status").toUpperCase()));
            order.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            order.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return order;
        }).stream().findFirst();
    }

    @Override
    public List<Order> findAllByUserId(Long userId) {
        String sql = "SELECT * FROM `order` WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) -> {
            Order order = new Order();
            order.setId(rs.getLong("id"));
            order.setUserId(rs.getLong("user_id"));
            order.setAddressId(rs.getLong("address_id"));
            order.setRestaurantId(rs.getLong("restaurant_id"));
            order.setTotalPrice(rs.getBigDecimal("total_price"));
            order.setStatus(Order.Status.valueOf(rs.getString("status").toUpperCase()));
            order.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            order.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return order;
        });
    }
}
