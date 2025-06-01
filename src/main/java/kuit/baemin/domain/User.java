package kuit.baemin.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    public User(String email, String password, String phoneNumber, String nickname) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
    }

    private Long userId;
    private String email;
    private String password;
    private String phoneNumber;
    private String nickname;
//    private String profile_image;
//    private String status;
//    private LocalDate created_at;
//    private LocalDate updated_at;
}
