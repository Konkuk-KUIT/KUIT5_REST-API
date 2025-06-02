package kuit.baemin.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class User {

    public User(String email, String password, String userId) {
        this.email = email;
        this.password = password;
        this.userId =userId;
    }
    @Id
    private String userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone_number;
    private String nickname;
    private String profile_image;
    private String status;
    private LocalDate created_at;
    private LocalDate updated_at;
    @PrePersist
    protected void onCreate() {
        this.created_at = LocalDate.now();
        this.updated_at = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updated_at = LocalDate.now();
    }

    public User() {

    }
}
