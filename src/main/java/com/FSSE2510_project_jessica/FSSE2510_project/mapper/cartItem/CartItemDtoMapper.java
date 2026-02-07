package com.FSSE2510_project_jessica.FSSE2510_project.mapper.cartItem;

import com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.Dto.CartItemRespDto;
import com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.domainObject.CartItemRespData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemDtoMapper {
    public CartItemRespDto toCartItemRespDto(CartItemRespData respData) {
        CartItemRespDto resp = new CartItemRespDto();
        resp.setName(respData.getProduct().getName());
        resp.setPid(respData.getProduct().getPid());
        resp.setCartQuantity(respData.getQuantity());
        resp.setPrice(respData.getProduct().getPrice());
        resp.setStock(respData.getProduct().getStock());
        resp.setImageUrl(respData.getProduct().getImageUrl());
        return resp;
    }
    public List<CartItemRespDto> toCartItemRespDtoList(List<CartItemRespData> cartItemRespDataList) {
        List<CartItemRespDto> cartItemRespDtoList = new ArrayList<>();
        for(CartItemRespData cartItemRespData: cartItemRespDataList){
            cartItemRespDtoList.add(toCartItemRespDto(cartItemRespData));
        }
        return cartItemRespDtoList;
    }
}
