package kuit.baemin.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderStatusLog {

    private Long id;

    private Long orderId;

    private Order.Status status;

    private LocalDateTime changedAt;
}
