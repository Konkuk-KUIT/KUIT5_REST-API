package kuit.baemin.dto.request;

import lombok.Getter;

@Getter
public class UserAddressRequest {
    private String label; // "집", "회사", "기타"
    private String addressName;
    private String roadAddress;
    private Double latitude;
    private Double longitude;
    private Boolean isDefault;
}
