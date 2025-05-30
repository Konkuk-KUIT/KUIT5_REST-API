package kuit.baemin.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Menu {


    private Long id;

    private Restaurant restaurant;

    private String name;

    private BigDecimal price;

    private String description;

    private Boolean isSoldOut;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}

