package kuit.baemin.repository.Order;

import kuit.baemin.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderStatusLogRepositoryV1 implements OrderStatusLogRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void save(Long orderId, Order.Status status) {
        String sql = "INSERT INTO order_status_log (order_id, status, changed_at) VALUES (?, ?, NOW())";
        jdbcTemplate.update(sql, orderId, status.name().toLowerCase());
    }
}
