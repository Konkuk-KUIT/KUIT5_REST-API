package kuit.baemin.dto.RequestDTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressRequestDTO {
    private String userId;     // 보통 snake_case 대신 camelCase 사용
    private String status;
    private String address;
}
