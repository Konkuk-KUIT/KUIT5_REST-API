package kuit.baemin.repository;

import kuit.baemin.dto.request.UserAddressRequest;
import kuit.baemin.dto.response.UserAddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserAddressRepository {
    private final JdbcTemplate jdbcTemplate;

    public Long save(UserAddressRequest request, Long userId)  {
        String sql = """
            INSERT INTO user_address 
            (label, address_name, road_address, latitude, longitude, is_default, status, created_at, updated_at, user_id)
            VALUES (?, ?, ?, ?, ?, ?, 'active', NOW(), NOW(), ?)
        """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, request.getLabel());
            ps.setString(2, request.getAddressName());
            ps.setString(3, request.getRoadAddress());
            ps.setDouble(4, request.getLatitude());
            ps.setDouble(5, request.getLongitude());
            ps.setBoolean(6, request.getIsDefault());
            ps.setLong(7, userId);
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
    public UserAddressResponse findById(Long id) {
        String sql = "SELECT * FROM user_address WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, userAddressRowMapper(), id);
    }

    public List<UserAddressResponse> findAllByUserId(Long userId) {
        String sql = "SELECT * FROM user_address WHERE user_id = ? AND status = 'active'";
        return jdbcTemplate.query(sql, userAddressRowMapper(), userId);
    }

    private RowMapper<UserAddressResponse> userAddressRowMapper() {
        return (rs, rowNum) -> {
            UserAddressResponse res = new UserAddressResponse();
            res.setId(rs.getLong("id"));
            res.setUserId(rs.getLong("user_id"));
            res.setLabel(rs.getString("label"));
            res.setAddressName(rs.getString("address_name"));
            res.setRoadAddress(rs.getString("road_address"));
            res.setLatitude(rs.getDouble("latitude"));
            res.setLongitude(rs.getDouble("longitude"));
            res.setIsDefault(rs.getBoolean("is_default"));
            res.setStatus(rs.getString("status"));
            res.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            res.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return res;
        };
    }

}
