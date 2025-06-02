//package kuit.baemin.repository;
//
//import com.mysql.cj.MysqlxSession;
//import kuit.baemin.domain.User;
//import kuit.baemin.repository.ex.MyDbException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.jdbc.datasource.DataSourceUtils;
//import org.springframework.jdbc.support.JdbcUtils;
//
//import javax.sql.DataSource;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 예외 누수 문제 해결
// * 체크 예외를 런타임 예외로 변경
// * MemberRepository 인터페이스 사용
// * throws SQLException 제거
// */
//@Slf4j
//public class UserRepositoryV4 implements UserRepository {
//
//    private final DataSource dataSource;
//
//    public UserRepositoryV4(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    public User save(User user)  {
//        String sql = "insert into user(email, password, nickname) " +
//                "values (?, ?, ?, ?, ?)";
//
//        Connection con = null;
//        PreparedStatement pstmt = null;
//
//        try {
//            con = getConnection();
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, user.getEmail());
//            pstmt.setString(2, user.getPassword());
//            pstmt.setString(4, user.getNickname());
//            pstmt.executeUpdate();
//            return user;
//        } catch (SQLException e) {
//            log.error("db error", e);
//            throw new MyDbException(e);
//        } finally {
//            // 커넥션은 종료하지 않음
//            close(con, pstmt, null);
//        }
//
//    }
//
//    @Override
//    public List<User> findAll() {
//        String sql = "SELECT * FROM user";
//
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try {
//            con = getConnection();
//            pstmt = con.prepareStatement(sql);
//            rs = pstmt.executeQuery();
//
//            List<User> users = new ArrayList<>();
//
//            while (rs.next()) {
//                User user = new User(rs.getString("email"), rs.getString("password"),rs.getString("user_id"));
//                user.setUserId(rs.getString("user_id"));
//                user.setPhone_number(rs.getString("phone_number"));
//                user.setNickname(rs.getString("nickname"));
//                user.setProfile_image(rs.getString("profile_image"));
//                user.setStatus(rs.getString("status"));
//
//                Date created = rs.getDate("created_at");
//                if (created != null) user.setCreated_at(created.toLocalDate());
//
//                Date updated = rs.getDate("updated_at");
//                if (updated != null) user.setUpdated_at(updated.toLocalDate());
//
//                users.add(user);
//            }
//
//            return users;
//        } catch (SQLException e) {
//            log.error("db error", e);
//            throw new MyDbException(e);
//        } finally {
//            close(con, pstmt, rs);
//        }
//    }
//
//    private Connection getConnection() {
//        return DataSourceUtils.getConnection(dataSource);
//    }
//
//    private void close(Connection con, Statement stmt, ResultSet rs) {
//        JdbcUtils.closeResultSet(rs);
//        JdbcUtils.closeStatement(stmt);
//        DataSourceUtils.releaseConnection(con, dataSource);
//    }
//
//}
