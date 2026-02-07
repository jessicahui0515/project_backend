package com.FSSE2510_project_jessica.FSSE2510_project.mapper.cartItem;

import com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.domainObject.CartItemRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.entity.CartItemEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.product.ProductDataMapper;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.user.UserDataMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemDataMapper {
    private final ProductDataMapper productDataMapper;
    private final UserDataMapper userDataMapper;

    public CartItemDataMapper(ProductDataMapper productDataMapper, UserDataMapper userDataMapper) {
        this.productDataMapper = productDataMapper;
        this.userDataMapper = userDataMapper;
    }

    public CartItemRespData toCartItemRespData(CartItemEntity entity) {
        CartItemRespData resp = new CartItemRespData();
        resp.setCid(entity.getCid());
        resp.setProduct(productDataMapper.toProductRespData(entity.getProduct()));
        resp.setUser(userDataMapper.toUserRespData(entity.getUser()));
        resp.setQuantity(entity.getQuantity());
        return resp;
    }

    public List<CartItemRespData> toCartItemRespDataList(List<CartItemEntity> CartItemEntityList) {
        List<CartItemRespData> cartItemRespDataList = new ArrayList<>();
        for(CartItemEntity cartItemEntity: CartItemEntityList){
            cartItemRespDataList.add(toCartItemRespData(cartItemEntity));
        }
        return cartItemRespDataList;
    }
}
