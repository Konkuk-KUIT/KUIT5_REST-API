package kuit.baemin.repository;

import kuit.baemin.domain.Review;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class ReviewRepositoryV6 implements ReviewRepository {

    private final JdbcTemplate jdbcTemplate;

    public ReviewRepositoryV6(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Review save(Review review) {
        String sql = "INSERT INTO review (store_id, user_id, scope, comment) VALUES (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, review.getStoreId());
            ps.setLong(2, review.getUserId());
            ps.setInt(3, review.getScope());
            ps.setString(4, review.getComment());
            return ps;
        }, keyHolder);

        Long reviewId = keyHolder.getKey().longValue();


        String selectSql = "SELECT * FROM review WHERE review_id = ?";

        Review savedReview = jdbcTemplate.queryForObject(selectSql,
                (rs, rowNum) -> new Review(
                        rs.getLong("review_id"),
                        rs.getLong("store_id"),
                        rs.getLong("user_id"),
                        rs.getInt("scope"),
                        rs.getString("comment"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ),
                reviewId);

        return savedReview;

    }

    public List<Review> findReviewsByStoreWithPaging(Long storeId, int size, Long lastReviewId) {
        String sql = "SELECT * FROM review " +
                "WHERE store_id = ? " +
                (lastReviewId != null ? "AND review_id < ? " : "") +
                "ORDER BY review_id DESC " +
                "LIMIT ?";

        List<Object> params = new ArrayList<>();
        params.add(storeId);
        if (lastReviewId != null) {
            params.add(lastReviewId);
        }
        params.add(size);

        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new Review(
                        rs.getLong("review_id"),
                        rs.getLong("store_id"),
                        rs.getLong("user_id"),
                        rs.getInt("scope"),
                        rs.getString("comment"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ),
                params.toArray()
        );
    }

}
