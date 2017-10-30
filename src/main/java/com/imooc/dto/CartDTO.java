package com.imooc.dto;

import lombok.Data;

/**
 * @Author Anita
 * @Date 2017/10/30 16:27
 */
@Data
public class CartDTO {
    /*商品id*/
    private String productId;

    /*商品数量*/
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
