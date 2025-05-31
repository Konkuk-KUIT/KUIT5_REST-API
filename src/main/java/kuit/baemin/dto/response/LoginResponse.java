package kuit.baemin.dto.response;

import kuit.baemin.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String userId;
    private String nickname;

    public static LoginResponse from(User user){
        return LoginResponse.builder()
                .userId(user.getUserId())
                .nickname(user.getNickname())
                .build();
    }
}
