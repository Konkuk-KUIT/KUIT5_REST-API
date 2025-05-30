package kuit.baemin.repository.Menu;

import kuit.baemin.domain.Menu;
import kuit.baemin.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MenuRepositoryV1 implements MenuRepository{
    private final JdbcTemplate jdbcTemplate;
    @Override
    public List<Menu> findAllByRestaurantId(Long restaurantId) {
        String sql = "SELECT * FROM menu WHERE restaurant_id = ?";

        return jdbcTemplate.query(sql, new Object[]{restaurantId}, (rs, rowNum) -> {
            Menu menu = new Menu();
            menu.setId(rs.getLong("id"));

            // restaurant는 최소한 id만 세팅해서 참조 (불필요한 추가 쿼리 방지)
            Restaurant restaurant = new Restaurant();
            restaurant.setId(rs.getLong("restaurant_id"));
            menu.setRestaurant(restaurant);

            menu.setName(rs.getString("name"));
            menu.setPrice(rs.getBigDecimal("price"));
            menu.setDescription(rs.getString("description"));
            menu.setIsSoldOut(rs.getBoolean("is_sold_out"));
            menu.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            menu.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

            return menu;
        });
    }

    @Override
    public Optional<Menu> findById(Long menuId) {
        String sql = "SELECT * FROM menu WHERE id = ?";
        List<Menu> menus = jdbcTemplate.query(sql, new Object[]{menuId}, (rs, rowNum) -> {
            Menu menu = new Menu();
            menu.setId(rs.getLong("id"));

            Restaurant restaurant = new Restaurant();
            restaurant.setId(rs.getLong("restaurant_id"));
            menu.setRestaurant(restaurant);

            menu.setName(rs.getString("name"));
            menu.setPrice(rs.getBigDecimal("price"));
            menu.setDescription(rs.getString("description"));
            menu.setIsSoldOut(rs.getBoolean("is_sold_out"));
            menu.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            menu.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());

            return menu;
        });

        return menus.stream().findFirst();
    }
}
