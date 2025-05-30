package kuit.baemin.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemOption {

    private Long id;

    private Long orderItemId;

    private Long menuOptionId;
}
