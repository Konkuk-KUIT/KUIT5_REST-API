package kuit.baemin.domain;

import jakarta.persistence.*;
import kuit.baemin.domain.enums.Status;
import kuit.baemin.dto.request.MenuRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "menu")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;
    private String description;
    private String imgUrl;
    private Status status = Status.ACTIVATED;
    private LocalDate created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    public void update(MenuRequest menuRequest) {
        this.price = menuRequest.getPrice();
        this.description = menuRequest.getDescription();
        this.imgUrl = menuRequest.getImgUrl();
        this.status = menuRequest.getStatus();
    }
}
