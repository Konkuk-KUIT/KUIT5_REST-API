package kuit.baemin.dto.response;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import kuit.baemin.domain.Menu;
import kuit.baemin.domain.Restaurant;
import kuit.baemin.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse {
    private String name;
    private int price;
    private String description;
    private String imgUrl;
    private LocalDate created_at;
    private Long restaurantId;

    public static MenuResponse from(Menu menu) {
        return MenuResponse.builder()
                .name(menu.getName())
                .price(menu.getPrice())
                .imgUrl(menu.getImgUrl())
                .description(menu.getDescription())
                .created_at(menu.getCreated_at())
                .restaurantId(menu.getRestaurant().getId())
                .build();
    }

}
