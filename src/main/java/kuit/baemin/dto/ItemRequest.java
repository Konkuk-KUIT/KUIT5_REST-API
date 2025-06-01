package kuit.baemin.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemRequest {

    @NotBlank
    private String itemTitle;

    @NotNull
    @DecimalMin("0.0")
    private BigDecimal itemPrice;

    private String itemPicture;

    private String itemExplanation;

    @NotNull
    private Long categoryId;
}
