package kuit.baemin.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAddressResponse {
    private Long addressId;
    private String detail;
    private String type;
    private Boolean isDefault;
}