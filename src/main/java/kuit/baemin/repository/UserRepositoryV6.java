package kuit.baemin.repository;

import kuit.baemin.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Date;
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
        String sql = "insert into user(email, password,user_id) " +
                "values (?, ?,?)";

        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getUserId());

        return user;
    }

    public boolean hasUserId(String id) {
        String sql = "SELECT COUNT(*) FROM user WHERE user_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public List<User> findAll() {
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            User user = new User(rs.getString("email"), rs.getString("password"),rs.getString("user_id"));
            user.setUserId(rs.getString("user_id"));
            user.setPhone_number(rs.getString("phone_number"));
            user.setNickname(rs.getString("nickname"));
            user.setProfile_image(rs.getString("profile_image"));
            user.setStatus(rs.getString("status"));

            Date created = rs.getDate("created_at");
            if (created != null) user.setCreated_at(created.toLocalDate());

            Date updated = rs.getDate("updated_at");
            if (updated != null) user.setUpdated_at(updated.toLocalDate());

            return user;
        });
    }

    public boolean deleteUserbyId(String id) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        jdbcTemplate.update(sql, id);
        return true;
    }

    public String updateUser(User user) {
        String sql = "update user set email = ?, password = ?, user_id = ? where user_id = ?";
        jdbcTemplate.update(sql, user.getEmail(), user.getPassword(), user.getUserId(), user.getUserId());
        return user.getUserId();
    }
}
