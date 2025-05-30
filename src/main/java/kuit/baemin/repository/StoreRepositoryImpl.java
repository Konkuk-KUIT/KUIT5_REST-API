package kuit.baemin.repository;

import kuit.baemin.domain.store.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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
        jdbcTemplate.update(sql,
                    store.getUserId(),
                    store.getAddressId(),
                    store.getName(),
                    store.getMinimumOrderPrice(),
                    store.getDeliveryFee()
        );
        return store;
    }
}
