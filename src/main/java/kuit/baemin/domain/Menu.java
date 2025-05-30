package kuit.baemin.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Menu {
    private Long id;
    private Long restaurantId;
    private String name;
    private Integer price;
    private String status;
    private String createdAt;
    private String updatedAt;
}
