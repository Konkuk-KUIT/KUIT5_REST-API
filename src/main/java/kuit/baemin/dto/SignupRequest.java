package kuit.baemin.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    @JsonCreator
    public SignupRequest(@JsonProperty("email") String email,
                         @JsonProperty("password") String password,
                         @JsonProperty("confirm_password") String confirmPassword,
                         @JsonProperty("nickname") String nickname,
                         @JsonProperty("phoneNumber") String phoneNumber) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    @Email
    @NotBlank
    private String email;
    @Size(min = 6, max = 20)
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String nickname;

    @NotBlank
    private String phoneNumber;
}
