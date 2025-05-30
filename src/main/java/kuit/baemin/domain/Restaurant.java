package kuit.baemin.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Restaurant {

    private Long id;

    private String name;

    private String category;

    private String roadAddress;

    private Double latitude;

    private Double longitude;

    private BigDecimal minimumOrderPrice;

    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum Status {
        ACTIVE, INACTIVE
    }
}