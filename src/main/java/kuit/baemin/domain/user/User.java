package kuit.baemin.domain.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private Long userId;
    private String addressId;
    private String email;
    private String password;
    private String phoneNumber;
    private UserGrade grade;
    private UserRole role;
    private String nickname;
    private String profileImage;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
