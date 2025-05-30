package kuit.baemin.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequest {
    private String detail;
    private double latitude;
    private double longitude;
    private String type; // "집", "회사", "기타"
    private boolean isDefault;
}
