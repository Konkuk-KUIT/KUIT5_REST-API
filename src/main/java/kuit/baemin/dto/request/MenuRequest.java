package kuit.baemin.dto.request;

import kuit.baemin.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuRequest {
    private String name;
    private int price;
    private String description;
    private String imgUrl;
    private Status status = Status.ACTIVATED;
}
