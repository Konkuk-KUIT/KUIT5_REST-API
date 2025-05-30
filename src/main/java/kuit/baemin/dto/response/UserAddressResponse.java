package kuit.baemin.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserAddressResponse {
    private Long id;
    private Long userId;
    private String label;
    private String addressName;
    private String roadAddress;
    private Double latitude;
    private Double longitude;
    private Boolean isDefault;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
