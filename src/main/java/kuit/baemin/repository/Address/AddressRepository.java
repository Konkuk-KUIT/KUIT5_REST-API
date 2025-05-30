package kuit.baemin.repository.Address;
import kuit.baemin.dto.AddressRequest;
import kuit.baemin.dto.UserAddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AddressRepository {

    private final JdbcTemplate jdbcTemplate;

    public Long saveAddress(Long userId, AddressRequest request) {
        String addressSql = "INSERT INTO address (detail, latitude, longitude, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        String userAddressSql = "INSERT INTO user_address (user_id, address_id, type, is_default) VALUES (?, ?, ?, ?)";

        LocalDateTime now = LocalDateTime.now();
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(addressSql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, request.getDetail());
            ps.setDouble(2, request.getLatitude());
            ps.setDouble(3, request.getLongitude());
            ps.setObject(4, now);
            ps.setObject(5, now);
            return ps;
        }, keyHolder);

        Long addressId = keyHolder.getKey().longValue();

        jdbcTemplate.update(userAddressSql,
                userId,
                addressId,
                request.getType(),
                request.isDefault());

        return addressId;
    }
    public List<UserAddressResponse> findAllByUserId(Long userId) {
        String sql = """
            SELECT a.id AS address_id, a.detail, ua.type, ua.is_default
            FROM user_address ua
            JOIN address a ON ua.address_id = a.id
            WHERE ua.user_id = ?
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new UserAddressResponse(
                        rs.getLong("address_id"),
                        rs.getString("detail"),
                        rs.getString("type"),
                        rs.getBoolean("is_default")
                ), userId);
    }
}
