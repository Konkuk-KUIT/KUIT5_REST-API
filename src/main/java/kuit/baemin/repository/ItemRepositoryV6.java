package kuit.baemin.repository;

import kuit.baemin.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Repository
public class ItemRepositoryV6 implements ItemRepository {

    private final JdbcTemplate jdbcTemplate;

    public ItemRepositoryV6(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Item save(Item item) {
        String sql = "INSERT INTO item (store_id, category_id, item_title, item_price, item_picture, item_explanation) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, item.getStoreId());
            ps.setLong(2, item.getCategoryId());
            ps.setString(3, item.getItemTitle());
            ps.setBigDecimal(4, item.getItemPrice());
            ps.setString(5, item.getItemPicture());
            ps.setString(6, item.getItemExplanation());
            return ps;
        }, keyHolder);

        item.setItemId(keyHolder.getKey().longValue());
        return item;
    }

    @Override
    public List<Item> findItemsByStoreWithPaging(Long storeId, int size, Long lastItemId) {
        String sql = "SELECT * FROM item " +
                "WHERE store_id = ? " +
                (lastItemId != null ? "AND item_id > ? " : "") +
                "ORDER BY item_id ASC " +
                "LIMIT ?";

        List<Object> params = new ArrayList<>();
        params.add(storeId);
        if (lastItemId != null) {
            params.add(lastItemId);
        }
        params.add(size);

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Item(
                        rs.getLong("item_id"),
                        rs.getLong("store_id"),
                        rs.getLong("category_id"),
                        rs.getString("item_title"),
                        rs.getBigDecimal("item_price"),
                        rs.getString("item_picture"),
                        rs.getString("item_explanation")
                ),
                params.toArray()
        );
    }


}

