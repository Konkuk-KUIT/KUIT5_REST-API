package kuit.baemin.dto;

import kuit.baemin.domain.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class MenuSimpleResponse {
    private Long menuId;
    private String name;
    private BigDecimal price;
    private boolean isSoldOut;

    public static MenuSimpleResponse from(Menu menu) {
        return new MenuSimpleResponse(
                menu.getId(),
                menu.getName(),
                menu.getPrice(),
                menu.getIsSoldOut()
        );
    }
}