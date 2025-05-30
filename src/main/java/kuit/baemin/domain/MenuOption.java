package kuit.baemin.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class MenuOption {
    private Long id;
    private Long optionGroupId;
    private String name;
    private BigDecimal extraPrice;
}