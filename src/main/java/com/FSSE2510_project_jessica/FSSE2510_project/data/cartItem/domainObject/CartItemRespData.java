package com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.domainObject;

import com.FSSE2510_project_jessica.FSSE2510_project.data.product.domainObject.ProductRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.domainObject.UserRespData;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CartItemRespData {
    private Integer cid;
    private ProductRespData product;
    private UserRespData user;
    private Integer quantity;
}
