package kuit.baemin.repository;

import kuit.baemin.domain.User;
import kuit.baemin.domain.UserGrade;
import kuit.baemin.exception.BusinessException;
import kuit.baemin.utils.BaseResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

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
        String sql = "insert into user(email, password, phone_number, nickname) " +
                "values (?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql,
                    user.getEmail(),
                    user.getPassword(),
                    user.getPhoneNumber(),
                    user.getNickname()
            );
            return user;
        } catch (DuplicateKeyException e) {
            // 이메일 중복일 때
            throw new BusinessException(BaseResponseStatus.DUPLICATED_EMAIL);
        }
    }

    // id로 찾은 user가 값이 있거나 없을 수 있기 때문에
    // 반환 타입을 Optional로 하기.
    @Override
    public User findById(Long id) {
        String sql = "select * from user where user_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql,
                    new Object[]{id},
                    (rs, rowNum) -> {
                        User u = new User(
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("phone_number"),
                                rs.getString("nickname")
                        );
                        // DB에서 꺼낸 값을 자바 필드에 덮어씌우기 -> DB에 처음 저장하고,
                        // 추후에 등급이 변할 수 있으므로 이렇게 새로 덮어 씌우는게 맞다함.
                        u.setGrade(UserGrade.fromKrName(rs.getString("grade")));
                        return u;
                    }
            );
        } catch (EmptyResultDataAccessException e) {
            throw new BusinessException(BaseResponseStatus.USER_NOT_FOUND);
        }
    }

    @Override
    public User findByEmail(String email) {
        String sql = "select * from user where email = ?";
        try {
            // RowMapper의 결과
            return jdbcTemplate.queryForObject(sql,
                    new Object[]{email},
                    (rs, rowNum) -> {
                        User u = new User(
                                rs.getString("email"),
                                rs.getString("password"),
                                rs.getString("phone_number"),
                                rs.getString("nickname")
                        );
                        // 메서드 전체의 결과 반환
                        return u;
                    }
            );
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

    @Override
    public void deleteById(Long id) {
        String sql = "delete from user where user_id = ?";
        int rows = jdbcTemplate.update(sql, id);
        if (rows == 0) {
            throw new BusinessException(BaseResponseStatus.USER_NOT_FOUND);
        }
    }
}
