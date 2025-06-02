package kuit.baemin.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest { //signup에 id 추가해서 구현해두기

    @JsonCreator
    public SignupRequest(@JsonProperty("email") String email,
                         @JsonProperty("password") String password,
                         @JsonProperty("confirm_password") String confirmPassword,
                         @JsonProperty("user_id")String userId) {
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.userId = userId;
    }

    private String userId;
    @Email
    private String email;
    @Size(min = 6, max = 20)
    private String password;
    private String confirmPassword;
}
