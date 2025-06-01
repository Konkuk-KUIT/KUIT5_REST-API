package kuit.baemin.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Item {

    private Long itemId;
    private Long storeId;
    private Long categoryId;
    private String itemTitle;
    private BigDecimal itemPrice;
    private String itemPicture;
    private String itemExplanation;

    public Item(Long itemId, Long storeId, Long categoryId, String itemTitle, BigDecimal itemPrice,
                String itemPicture, String itemExplanation) {
        this.itemId = itemId;
        this.storeId = storeId;
        this.categoryId = categoryId;
        this.itemTitle = itemTitle;
        this.itemPrice = itemPrice;
        this.itemPicture = itemPicture;
        this.itemExplanation = itemExplanation;
    }
}