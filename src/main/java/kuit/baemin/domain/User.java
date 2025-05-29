package kuit.baemin.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class User {

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    private Long userId;
    private String email;
    private String password;
    private String phoneNumber;
    public String grade;
    private String nickname;
    private String profileImage;
    private String status;
    private LocalDate created_at;
    private LocalDate updated_at;
}
