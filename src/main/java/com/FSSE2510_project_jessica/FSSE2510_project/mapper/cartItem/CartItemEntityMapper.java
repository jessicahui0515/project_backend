package com.FSSE2510_project_jessica.FSSE2510_project.mapper.cartItem;

import com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.entity.CartItemEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.product.entity.ProductEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class CartItemEntityMapper {
    public CartItemEntity toCartItemEntity (UserEntity userEntity, ProductEntity productEntity, Integer quantity){
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setUser(userEntity);
        cartItemEntity.setProduct(productEntity);
        cartItemEntity.setQuantity(quantity);
        return  cartItemEntity;

    }
}
