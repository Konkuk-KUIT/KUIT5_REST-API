package kuit.baemin.repository;

import kuit.baemin.domain.User;
import kuit.baemin.exception.BusinessException;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * JdbcTemplate 사용
 */
@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public User save(User user)  {
        String sql = "insert into user(email, password) " +
                "values (?, ?)";

        jdbcTemplate.update(sql, user.getEmail(), user.getPassword());

        return user;
    }

    // id로 찾은 user가 값이 있거나 없을 수 있기 때문에
    // 반환 타입을 Optional로 하기.
    @Override
    public User findById(Long id) {
        String sql = "select * from user where user_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql,
                    new Object[]{id}, (rs, rowNum) -> new User(rs.getString("email"), rs.getString("password")));
        } catch (EmptyResultDataAccessException e) {
            throw new BusinessException(BaseResponseStatus.USER_NOT_FOUND);
        }
    }

    // 전체 회원 조회
    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new User(rs.getString("email"), rs.getString("password")));
    }

    @Override
    public void updatePassword(Long id, String newPassword) {
        String sql = "update user set password = ? where user_id = ?";
        jdbcTemplate.update(sql, newPassword, id);
    }
}
