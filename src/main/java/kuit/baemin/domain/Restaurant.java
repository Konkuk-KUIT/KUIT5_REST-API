package kuit.baemin.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate createdAt;
    private int minOrderPrice;
    private int deliveryFee;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;
}

