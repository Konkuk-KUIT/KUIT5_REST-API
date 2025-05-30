package kuit.baemin.repository.Order;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderItemOptionRepositoryV1 implements OrderItemOptionRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Long orderItemId, Long menuOptionId) {
        String sql = "INSERT INTO order_item_option (order_item_id, menu_option_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, orderItemId, menuOptionId);
    }


    @Override
    public List<Long> findOptionIdsByOrderItemId(Long orderItemId) {
        String sql = "SELECT menu_option_id FROM order_item_option WHERE order_item_id = ?";
        return jdbcTemplate.query(sql, new Object[]{orderItemId}, (rs, rowNum) -> rs.getLong("menu_option_id"));
    }
}
