package kuit.baemin.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Review {

    private Long reviewId;
    private Long storeId;
    private Long userId;
    private Integer scope;
    private String comment;
    private LocalDateTime createdAt;

    public Review(Long reviewId, Long storeId, Long userId, Integer scope, String comment, LocalDateTime createdAt) {
        this.reviewId = reviewId;
        this.storeId = storeId;
        this.userId = userId;
        this.scope = scope;
        this.comment = comment;
        this.createdAt = createdAt;
    }

}
