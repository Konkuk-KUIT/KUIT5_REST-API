package kuit.baemin.repository;

import kuit.baemin.domain.Store;
import kuit.baemin.dto.OrderItemRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import kuit.baemin.domain.Order;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class OrderRepositoryV6 implements OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepositoryV6(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Order save(Order order) {
        String sql = "INSERT INTO orders (user_id, store_id) VALUES (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, order.getUserId());
            ps.setLong(2, order.getStoreId());
            return ps;
        }, keyHolder);

        Long orderId = keyHolder.getKey().longValue();
        order.setOrderId(orderId);


        String detailSql = "INSERT INTO order_detail (order_id, item_id, quantity, purchase_price, category_id) " +
                "VALUES (?, ?, ?, ?, ?)";

        String itemSql = "SELECT item_price, category_id FROM item WHERE item_id = ?";

        for (OrderItemRequest item : order.getOrderItems()) {

            Map<String, Object> itemData = jdbcTemplate.queryForMap(itemSql, item.getItemId());

            Double itemPrice = ((Number) itemData.get("item_price")).doubleValue();
            Long categoryId = ((Number) itemData.get("category_id")).longValue();

            jdbcTemplate.update(detailSql,
                    orderId,
                    item.getItemId(),
                    item.getQuantity(),
                    itemPrice,
                    categoryId
            );
        }


        return order;
    }


    @Override
    public List<Order> findOrdersByUserIdWithPaging(Long userId, int size, Long lastOrderId) {
        String sql = "SELECT * FROM orders " +
                "WHERE user_id = ? " +
                (lastOrderId != null ? "AND order_id < ? " : "") +
                "ORDER BY order_id DESC " +
                "LIMIT ?";

        List<Object> params = new ArrayList<>();
        params.add(userId);
        if (lastOrderId != null) {
            params.add(lastOrderId);
        }
        params.add(size);

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Order(
                            rs.getLong("order_id"),
                            rs.getLong("user_id"),
                            rs.getLong("store_id"),
                            rs.getString("order_status"),
                            rs.getTimestamp("order_datetime").toLocalDateTime()
                ),
                params.toArray()
        );
    }

}