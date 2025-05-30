package kuit.baemin.dto;

import kuit.baemin.domain.MenuOption;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class OptionResponse {
    private Long optionId;
    private String name;
    private BigDecimal extraPrice;

    public static OptionResponse from(MenuOption option) {
        return new OptionResponse(
                option.getId(),
                option.getName(),
                option.getExtraPrice()
        );
    }
}
