package kuit.baemin.domain.address;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Addresses {
    // 새 객체를 생성할 때 디폴트 값
    public Addresses(String city, String district, String streetAddress, String aptNumber) {
        this.city = city;
        this.district = district;
        this.streetAddress = streetAddress;
        this.aptNumber = aptNumber;
        this.status = AddressStatus.ACTIVE;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    private Long addressId;
    private String city;
    private String district;
    private String streetAddress;
    private String aptNumber;
    private AddressStatus status;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
