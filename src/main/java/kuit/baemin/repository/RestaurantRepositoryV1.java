package kuit.baemin.repository;

import kuit.baemin.domain.Restaurant;
import kuit.baemin.rowmapper.AddressRowMapper;
import kuit.baemin.rowmapper.RestaurantRowMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class RestaurantRepositoryV1 implements RestaturantRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<Restaurant> findAll() {
        String sql = "SELECT * FROM restaurant";
        return jdbcTemplate.query(sql,new RestaurantRowMapper());
    }

    @Override
    public Restaurant findById(Long restaurantId) {
        return null;
    }
}
