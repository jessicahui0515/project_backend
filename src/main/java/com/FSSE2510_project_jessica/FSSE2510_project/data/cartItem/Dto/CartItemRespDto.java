package com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.Dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CartItemRespDto {
        private Integer pid;
        private String name;
        private String imageUrl;
        private BigDecimal price;
        private Integer cartQuantity;
        private Integer stock;

}
