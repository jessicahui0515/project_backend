package com.FSSE2510_project_jessica.FSSE2510_project.service;

import com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.domainObject.CartItemRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.domainObject.FirebaseUserReqData;

import java.util.List;

public interface CartItemService {
    void putCartItem(FirebaseUserReqData userData, Integer pid, Integer quantity);

    List<CartItemRespData> getCartItem(FirebaseUserReqData firebaseUserReqData);

    void updateCartQuantity(FirebaseUserReqData userData, Integer pid, Integer quantity);

    void deleteCartItem(FirebaseUserReqData userData, Integer pid);
}
