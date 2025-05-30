package kuit.baemin.repository.User;

import kuit.baemin.domain.User;
import kuit.baemin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryV7 implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public User save(User user) {
        String sql = "INSERT INTO user (name, phone, email, created_at, updated_at) VALUES (?, ?, ?, ?, ?)";
        // DB가 생성한 id값을 가져오기 위해
        KeyHolder keyHolder = new GeneratedKeyHolder();

        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getPhone());
            ps.setString(3, user.getEmail());
            ps.setObject(4, now);
            ps.setObject(5, now);
            return ps;
        }, keyHolder);

        user.setId(keyHolder.getKey().longValue());
        user.setCreatedAt(now);
        user.setUpdatedAt(now);
        return user;
    }
    @Override
    public Optional<User> findById(Long userId) {
        String sql = "SELECT * FROM user WHERE id = ?";

        try {
            User user = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                User u = new User();
                u.setId(rs.getLong("id"));
                u.setName(rs.getString("name"));
                u.setPhone(rs.getString("phone"));
                u.setEmail(rs.getString("email"));
                u.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                u.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                return u;
            }, userId);

            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM user WHERE email = ?";

        try {
            User user = jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
                User u = new User();
                u.setId(rs.getLong("id"));
                u.setName(rs.getString("name"));
                u.setPhone(rs.getString("phone"));
                u.setEmail(rs.getString("email"));
                u.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                u.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
                return u;
            }, email);

            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

}
