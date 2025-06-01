package kuit.baemin.repository;

import kuit.baemin.domain.store.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Slf4j
@Repository
public class StoreRepositoryImpl implements StoreRepository{

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
}
