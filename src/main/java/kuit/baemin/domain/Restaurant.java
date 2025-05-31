package kuit.baemin.domain;

import jakarta.persistence.*;
import kuit.baemin.domain.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurant")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    private String phoneNumber;
    private String createdAt;
    private int minOrderPrice;
    private int deliveryFee;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;
}

