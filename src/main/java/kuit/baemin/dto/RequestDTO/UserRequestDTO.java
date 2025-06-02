package kuit.baemin.dto.RequestDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    String name;
    String email;
    String nickname;
    String role;
    long address;
}
