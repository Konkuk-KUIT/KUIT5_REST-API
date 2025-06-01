package kuit.baemin.repository.address;

import kuit.baemin.domain.address.UserAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;

@Repository
@Slf4j
public class UserAddressRepositoryImpl implements UserAddressRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserAddressRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(UserAddress userAddress) {
        String sql = "INSERT INTO user_address (user_id, address_id, address_type, status, created_at, modified_at) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                userAddress.getUserId(),
                userAddress.getAddressId(),
                userAddress.getAddressType().name(),
                userAddress.getStatus().name(),
                Timestamp.valueOf(userAddress.getCreatedAt()),
                Timestamp.valueOf(userAddress.getUpdatedAt())
        );
    }
}
