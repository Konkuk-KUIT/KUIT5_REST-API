package kuit.baemin.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PasswordChangeRequest {
    @JsonCreator
    public PasswordChangeRequest(@JsonProperty("current_password") String currentPassword,
                                 @JsonProperty("new_password")String newPassword,
                                 @JsonProperty("new_password_confirm") String newPasswordConfirm) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.newPasswordConfirm = newPasswordConfirm;
    }


    @NotBlank
    private String currentPassword;
    @Size(min = 6, max = 20)
    private String  newPassword;
    @NotBlank
    private String  newPasswordConfirm;
}
