package kuit.baemin.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Store {
    private Long storeId;
    private String storeName;
    private String location;
    private Integer minimumPrice;
    private Boolean activate;
    private Long categoryId;

    public Store(Long storeId, String storeName, String location, Integer minimumPrice, Boolean activate, Long categoryId) {
        this.storeId = storeId;
        this.storeName = storeName;
        this.location = location;
        this.minimumPrice = minimumPrice;
        this.activate = activate;
        this.categoryId = categoryId;
    }


}
