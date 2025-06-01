package kuit.baemin.repository;

import kuit.baemin.domain.Store;
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
import java.util.Optional;

@Slf4j
@Repository
public class StoreRepositoryV6 implements StoreRepository {
    private final JdbcTemplate jdbcTemplate;

    public StoreRepositoryV6(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Store save(Store store) {
        String sql = "INSERT INTO store (store_name, location, minimum_price, activate, category_id) " +
                "VALUES (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, store.getStoreName());
            ps.setString(2, store.getLocation());
            ps.setInt(3, store.getMinimumPrice());
            ps.setBoolean(4, store.getActivate());
            ps.setLong(5, store.getCategoryId());
            return ps;
        }, keyHolder);

        store.setStoreId(keyHolder.getKey().longValue());
        return store;
    }

    @Override
    public Optional<Store> findById(Long storeId) {
        String sql = "SELECT * FROM store WHERE store_id = ?";

        return jdbcTemplate.query(sql,
                rs -> {
                    if (rs.next()) {
                        Store store = new Store(
                                rs.getLong("store_id"),
                                rs.getString("store_name"),
                                rs.getString("location"),
                                rs.getInt("minimum_price"),
                                rs.getBoolean("activate"),
                                rs.getLong("category_id")
                        );
                        return Optional.of(store);
                    } else {
                        return Optional.empty();
                    }
                }, storeId);
    }

    @Override
    public List<Store> findStoresByCategoryWithPaging(Long categoryId, int size, Long lastStoreId) {
        String sql = "SELECT * FROM store " +
                "WHERE category_id = ? " +
                (lastStoreId != null ? "AND store_id > ? " : "") +
                "ORDER BY store_id ASC " +
                "LIMIT ?";

        List<Object> params = new ArrayList<>();
        params.add(categoryId);
        if (lastStoreId != null) {
            params.add(lastStoreId);
        }
        params.add(size);

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Store(
                        rs.getLong("store_id"),
                        rs.getString("store_name"),
                        rs.getString("location"),
                        rs.getInt("minimum_price"),
                        rs.getBoolean("activate"),
                        rs.getLong("category_id")
                ),
                params.toArray()
        );
    }

}
