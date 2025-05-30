package kuit.baemin.repository.Menu;

import kuit.baemin.domain.MenuOption;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MenuOptionRepositoryV1 implements MenuOptionRepository{
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<MenuOption> findAllByOptionGroupId(Long optionGroupId) {
        String sql = "SELECT * FROM menu_option WHERE option_group_id = ?";
        return jdbcTemplate.query(sql, new Object[]{optionGroupId}, (rs, rowNum) -> {
            MenuOption option = new MenuOption();
            option.setId(rs.getLong("id"));
            option.setOptionGroupId(rs.getLong("option_group_id"));
            option.setName(rs.getString("name"));
            option.setExtraPrice(rs.getBigDecimal("extra_price"));
            return option;
        });
    }

    @Override
    public Optional<MenuOption> findById(Long id) {
        String sql = "SELECT * FROM menu_option WHERE id = ?";
        List<MenuOption> options = jdbcTemplate.query(sql, new Object[]{id}, (rs, rowNum) -> {
            MenuOption option = new MenuOption();
            option.setId(rs.getLong("id"));
            option.setOptionGroupId(rs.getLong("option_group_id"));
            option.setName(rs.getString("name"));
            option.setExtraPrice(rs.getBigDecimal("extra_price"));
            return option;
        });

        return options.stream().findFirst();
    }

    @Override
    public List<MenuOption> findAllByIds(List<Long> optionIds) {
        if (optionIds == null || optionIds.isEmpty()) {
            return List.of();
        }

        String placeholders = String.join(",", optionIds.stream().map(id -> "?").toList());

        String sql = "SELECT * FROM menu_option WHERE id IN (" + placeholders + ")";

        return jdbcTemplate.query(sql, optionIds.toArray(), (rs, rowNum) -> {
            MenuOption option = new MenuOption();
            option.setId(rs.getLong("id"));
            option.setOptionGroupId(rs.getLong("option_group_id"));
            option.setName(rs.getString("name"));
            option.setExtraPrice(rs.getBigDecimal("extra_price"));
            return option;
        });
    }
}
