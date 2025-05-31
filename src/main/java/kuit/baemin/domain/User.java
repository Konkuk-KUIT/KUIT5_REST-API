package kuit.baemin.domain;

import jakarta.persistence.*;
import kuit.baemin.dto.request.UserRequest;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private String password;
    private String phoneNumber;

    private String nickname;
    private String profile_image;

    private LocalDate created_at;

    public void update(UserRequest userRequest) {
       this.email = userRequest.getEmail();
       this.nickname = userRequest.getNickname();
    }
}
