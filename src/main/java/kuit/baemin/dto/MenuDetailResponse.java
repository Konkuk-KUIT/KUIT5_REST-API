package kuit.baemin.dto;

import kuit.baemin.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
public class MenuDetailResponse {
    private Long menuId;
    private String name;
    private BigDecimal price;
    private String description;
    private List<OptionGroupResponse> optionGroups;

    public static MenuDetailResponse of(Menu menu, List<OptionGroupResponse> groups) {
        return new MenuDetailResponse(
                menu.getId(),
                menu.getName(),
                menu.getPrice(),
                menu.getDescription(),
                groups
        );
    }
}