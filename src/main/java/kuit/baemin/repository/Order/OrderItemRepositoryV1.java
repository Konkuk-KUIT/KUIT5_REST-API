package kuit.baemin.repository.Order;

import kuit.baemin.domain.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemRepositoryV1 implements OrderItemRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Long orderId, Long menuId, int quantity, BigDecimal itemPrice) {
        String sql = "INSERT INTO order_item (order_id, menu_id, quantity, item_price) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, orderId);
            ps.setLong(2, menuId);
            ps.setInt(3, quantity);
            ps.setBigDecimal(4, itemPrice);
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public List<OrderItem> findAllByOrderId(Long orderId) {
        String sql = "SELECT * FROM order_item WHERE order_id = ?";
        return jdbcTemplate.query(sql, new Object[]{orderId}, (rs, rowNum) -> {
            OrderItem item = new OrderItem();
            item.setId(rs.getLong("id"));
            item.setOrderId(rs.getLong("order_id"));
            item.setMenuId(rs.getLong("menu_id"));
            item.setQuantity(rs.getInt("quantity"));
            item.setItemPrice(rs.getBigDecimal("item_price"));
            return item;
        });
    }

}
