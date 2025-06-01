package kuit.baemin.dto.request.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
//@Setter
public class SignupRequest {

    @JsonCreator
    public SignupRequest(@JsonProperty("email") String email,
                         @JsonProperty("password") String password,
                         @JsonProperty("confirm_password") String confirmPassword,
                         @JsonProperty("phone_number") String phoneNumber,
                         @JsonProperty("nickname") String nickname
                         ) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
    }

    @Email
    private String email;
    @Size(min = 6, max = 20)
    private String password;
    private String confirmPassword;
    @Size(min = 11, max = 11, message = "핸드폰 번호는 11자리입니다.")
    private String phoneNumber;
    @Size(max = 30)
    private String nickname;

    // todo : 주소 추가하기.
}
