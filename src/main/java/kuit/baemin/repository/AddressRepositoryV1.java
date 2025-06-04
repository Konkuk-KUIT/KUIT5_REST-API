package kuit.baemin.repository;

import kuit.baemin.domain.Address;
import kuit.baemin.domain.User;
import kuit.baemin.rowmapper.AddressRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * JdbcTemplate 사용
 */
@Slf4j
@Repository
public class AddressRepositoryV1 implements AddressRepository{
    private final JdbcTemplate jdbcTemplate;

    public AddressRepositoryV1(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Address findById(Long addressId) {
        String sql = "SELECT * FROM address WHERE address_id = ?";
        return jdbcTemplate.queryForObject(sql, new AddressRowMapper(), addressId);
    }

    @Override
    public List<Address> findAll() {
        String sql = "SELECT * FROM address";
        return jdbcTemplate.query(sql,new AddressRowMapper());
    }

    @Override
    public Address save(Address address) {
        String sql = "INSERT INTO address(user_id, address, created_date, modified_date, status) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,address.getUserId(),address.getAddress(),address.getCrearedDate(), address.getModifiedDate(),address.getStatus());
        return address;
    }
}
