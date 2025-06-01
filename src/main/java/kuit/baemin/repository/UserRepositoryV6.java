package kuit.baemin.repository;

import kuit.baemin.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

/**
 * JdbcTemplate 사용
 */
@Slf4j
@Repository
public class UserRepositoryV6 implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryV6(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public User save(User user)  {
        String sql = "insert into users(email, password, phone_number, nickname) " +
                "values (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getNickname());
            return ps;
        }, keyHolder);

        user.setUserId(keyHolder.getKey().longValue());

        return user;
    }

    @Override
    public boolean existsByEmail(String email) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        return jdbcTemplate.query(sql,
                rs -> {
                    if (rs.next()) {
                        User user = new User(
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("phone_number"),
                                rs.getString("nickname")
                        );
                        user.setUserId(rs.getLong("user_id"));
                        return Optional.of(user);
                    } else {
                        return Optional.empty();
                    }
                }, email);
    }


}
