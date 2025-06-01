package kuit.baemin.dto.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

// 조회용 Response DTO -> 화면에 실제로 보여줘야 하는 필드만 이상적.
@Getter
public class UserResponse {
    @JsonCreator
    public UserResponse(@JsonProperty("email") String email,
                        @JsonProperty("nickname")String nickname,
                        @JsonProperty("phone_number")String phoneNumber,
                        @JsonProperty("grade")String grade,
                        @JsonProperty("profile_image")String profileImage) {
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.grade = grade;
        this.profileImage = profileImage;
    }
    private String email;
    private String nickname;
    private String phoneNumber;
    private String grade;
    private String profileImage;

}