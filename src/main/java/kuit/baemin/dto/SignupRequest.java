package kuit.baemin.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
//@Setter
public class SignupRequest {

    @JsonCreator
    public SignupRequest(@JsonProperty("name") String name,
                         @JsonProperty("phone") String phone,
                         @JsonProperty("email") String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    private String name;
    private String phone;
    @Email
    private String email;
}
