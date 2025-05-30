package kuit.baemin.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuResponse {
    private Long id;
    private Long restaurantId;
    private String name;
    private Integer price;
}
