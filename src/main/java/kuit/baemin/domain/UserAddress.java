package kuit.baemin.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserAddress {
    private Long id;
    private String label; // enum: "집", "회사", "기타"
    private String addressName;
    private String roadAddress;
    private Double latitude;
    private Double longitude;
    private Boolean isDefault;
    private String status; // enum: "active", "deleted", "dormant"
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long userId;
}
