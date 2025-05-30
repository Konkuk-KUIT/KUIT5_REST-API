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

    public User(String email, String password, String phoneNumber, String nickname) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
        this.role = UserRole.USER;
        this.grade = UserGrade.VALUED;
    }

    private Long userId;
    private String email;
    private String password;
    private String phoneNumber;
    public UserGrade grade;
    public UserRole role;
    private String nickname;
    private String profileImage;
    private String status;
    private LocalDate created_at;
    private LocalDate updated_at;
}
