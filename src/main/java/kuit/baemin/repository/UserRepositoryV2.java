package kuit.baemin.repository;

import kuit.baemin.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;

import java.sql.*;

/**
 * JDBC - ConnectionParam
 */
@Slf4j
public class UserRepositoryV2 {

    public User save(Connection con, User user) throws SQLException {
        String sql = "insert into users(email, password, phone_number, nickname) " +
                "values (?, ?, ?, ?)";

        PreparedStatement pstmt = null;

        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getPhoneNumber());
            pstmt.setString(4, user.getNickname());

            pstmt.executeUpdate();
            return user;
        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        } finally {
            // 커넥션은 종료하지 않음
            JdbcUtils.closeStatement(pstmt);
        }

    }

}
