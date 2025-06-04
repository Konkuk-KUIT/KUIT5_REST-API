package kuit.baemin.rowmapper;

import kuit.baemin.domain.Address;
import kuit.baemin.domain.Restaurant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RestaurantRowMapper implements RowMapper<Restaurant> {
    @Override
    public Restaurant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Restaurant restaurant = new Restaurant(
                rs.getString("name"),
                rs.getString("category"),
                rs.getString("address"),
                rs.getString("pictureUrl"),
                rs.getString("phone"),
                rs.getString("content"),
                rs.getInt("min_delivery_price")
        );
        restaurant.setCrearedDate(rs.getTimestamp("created_date"));
        restaurant.setModifiedDate(rs.getTimestamp("modified_date"));
        return address;
    }
}