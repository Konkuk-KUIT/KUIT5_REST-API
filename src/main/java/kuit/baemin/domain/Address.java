package kuit.baemin.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
public class Address {
    public Address(Long userId, String address, String status) {
        this.userId = userId;
        this.address = address;
        this.crearedDate = Timestamp.valueOf(LocalDateTime.now());
        this.modifiedDate = Timestamp.valueOf(LocalDateTime.now());
        this.status = status;
    }

    private Long addressId;
    private Long userId;
    private String address;
    private Timestamp crearedDate;
    private Timestamp modifiedDate;
    private String status;
}
