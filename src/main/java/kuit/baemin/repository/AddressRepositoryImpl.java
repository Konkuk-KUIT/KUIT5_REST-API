package kuit.baemin.repository;

import kuit.baemin.domain.address.Addresses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
@Slf4j
public class AddressRepositoryImpl implements AddressRepository{

    private final JdbcTemplate jdbcTemplate;

    public AddressRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Addresses save(Addresses addresses) {
        String sql = "insert into addresses(city, district, street_address, apt_number) " +
                "values (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                    addresses.getCity(),
                    addresses.getDistrict(),
                    addresses.getStreetAddress(),
                    addresses.getAptNumber());
        return addresses;

    }
}
