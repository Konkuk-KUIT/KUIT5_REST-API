package kuit.baemin.repository.Menu;

import kuit.baemin.domain.MenuOptionGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuOptionGroupRepositoryV1 implements MenuOptionGroupRespository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<MenuOptionGroup> findAllByMenuId(Long menuId) {
        String sql = "SELECT * FROM menu_option_group WHERE menu_id = ?";
        return jdbcTemplate.query(sql, new Object[]{menuId}, (rs, rowNum) -> {
            MenuOptionGroup group = new MenuOptionGroup();
            group.setId(rs.getLong("id"));
            group.setMenuId(rs.getLong("menu_id"));
            group.setName(rs.getString("name"));
            group.setOptionType(rs.getString("option_type"));
            group.setIsRequired(rs.getBoolean("is_required"));
            return group;
        });
    }
}
