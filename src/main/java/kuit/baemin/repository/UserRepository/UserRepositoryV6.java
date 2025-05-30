package kuit.baemin.repository.UserRepository;

import kuit.baemin.domain.User;
import kuit.baemin.dto.response.RestaurantResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

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
        String sql = "insert into user(email, password) " +
                "values (?, ?)";

        jdbcTemplate.update(sql, user.getEmail(), user.getPassword());

        return user;
    }

    @Transactional
    public List<RestaurantResponse> findFavoriteRestaurantsByUserId(Long userId) {
        String sql = """
            
            SELECT r.id, r.name, r.latitude, r.longitude, r.min_order_price, r.delivery_fee
            FROM user_favorite uf
            JOIN restaurant r ON uf.restaurant_id = r.id
            WHERE uf.user_id = ?
            """;

        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(RestaurantResponse.class), userId);
    }

}
