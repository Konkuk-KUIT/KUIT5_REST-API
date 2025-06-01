package kuit.baemin.dto;

import jakarta.validation.constraints.*;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {

    @NotNull
    @Min(1)
    @Max(5)
    private Integer scope;

    @NotBlank
    private String comment;
}
