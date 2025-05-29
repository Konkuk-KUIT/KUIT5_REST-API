package kuit.baemin.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String email;
    private String userId;
    private String password;
    private String phoneNumber;

    private String nickname;
    private String profile_image;
}
