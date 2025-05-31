package kuit.baemin.repository.address;

import kuit.baemin.domain.address.AddressStatus;
import kuit.baemin.domain.address.Addresses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

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

    public Optional<Addresses> findById(Long id) {
        String sql = "SELECT * FROM addresses WHERE address_id = ?";
        try {
            // 행을 row로 매핑해주기
            Addresses addresses = jdbcTemplate.queryForObject(
                    sql,
                    new Object[]{id},
                    (rs, rowNum) -> {
                        Addresses a = new Addresses(
                                rs.getString("city"),
                                rs.getString("district"),
                                rs.getString("street_address"),
                                rs.getString("apt_number")
                        );

                        // 다시 덮어씌우기. 위에 생성자를 부르면 현재 시간으로 정해지기 때문에
                        // rs.get~을 통해서 DB에 저장된 레코드를 가져온다.
                        a.setAddressId(rs.getLong("address_id"));

                        a.setStatus(AddressStatus.valueOf(rs.getString("status")));
                        a.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                        a.setUpdated_at(rs.getTimestamp("modified_at").toLocalDateTime());

                        return a;
                    }
            );
            return Optional.of(addresses);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
