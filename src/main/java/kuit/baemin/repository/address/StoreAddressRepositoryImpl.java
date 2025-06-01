package kuit.baemin.repository.address;

import kuit.baemin.domain.address.StoreAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
@Slf4j
public class StoreAddressRepositoryImpl implements StoreAddressRepository{

    private final JdbcTemplate jdbcTemplate;

    public StoreAddressRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public void save(StoreAddress storeAddress) {
        String sql = "insert into store_address (store_id, address_id) " +"values (?, ?)";
        jdbcTemplate.update(sql, storeAddress.getStoreId(), storeAddress.getAddressId());
    }


}
