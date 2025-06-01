package kuit.baemin.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Email;

@Getter
public class LoginRequest {
    @JsonCreator
    public LoginRequest(@JsonProperty("email") String email,
                        @JsonProperty("password") String password) {
        this.email = email;
        this.password = password;
    }

    // validator 기능 대신에 어노테이션
    @NotBlank(message = "email는 필수 입력입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "password는 필수 입력입니다.")
    private String password;
}
