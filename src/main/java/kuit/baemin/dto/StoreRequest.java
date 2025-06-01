package kuit.baemin.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreRequest {
    @NotBlank
    private String storeName;

    @NotBlank
    private String location;

    @NotNull
    @Min(0)
    private Integer minimumPrice;

    private Boolean activate = true;

    @NotNull
    private Long categoryId;
}
