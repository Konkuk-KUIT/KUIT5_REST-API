package kuit.baemin.repository;

import kuit.baemin.domain.store.Store;
import kuit.baemin.domain.store.StoreStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

@Slf4j
@Repository
public class StoreRepositoryImpl implements StoreRepository {

    private final JdbcTemplate jdbcTemplate;

    public StoreRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Store save(Store store) {
        String sql = "insert into store(user_id, address_id, name, minimum_order_price, delivery_fee) " +
                "values (?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, store.getUserId());
            ps.setLong(2, store.getAddressId());
            ps.setString(3, store.getName());
            ps.setInt(4, store.getMinimumOrderPrice());
            ps.setInt(5, store.getDeliveryFee());
            return ps;
        }, keyHolder);
        Long generatedStoreId = keyHolder.getKey().longValue();
        store.setStoreId(generatedStoreId);
        return store;
    }

    @Override
    public Optional<Store> findById(Long id) {
        String sql = "select * from store where store_id = ?";
        try {
            Store store = jdbcTemplate.queryForObject(sql,
                    new Object[]{id},
                    (rs, rowNum) -> {
                        Store s = new Store(
                                rs.getLong("store_id"),
                                rs.getLong("user_id"),
                                rs.getString("name"),
                                rs.getInt("minimum_order_price"),
                                rs.getInt("delivery_fee")
                        );

                        s.setAddressId(rs.getLong("address_id"));
                        s.setStatus(StoreStatus.valueOf(rs.getString("status")));
                        s.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                        s.setUpdatedAt(rs.getTimestamp("modified_at").toLocalDateTime());

                        return s;
                    }
            );
            return Optional.of(store);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
