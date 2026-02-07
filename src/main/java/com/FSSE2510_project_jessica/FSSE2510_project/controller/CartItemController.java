package com.FSSE2510_project_jessica.FSSE2510_project.controller;

import com.FSSE2510_project_jessica.FSSE2510_project.config.EnvConfig;
import com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.Dto.CartItemRespDto;
import com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.domainObject.CartItemRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.domainObject.FirebaseUserReqData;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.cartItem.CartItemDataMapper;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.cartItem.CartItemDtoMapper;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.user.UserDataMapper;
import com.FSSE2510_project_jessica.FSSE2510_project.service.CartItemService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart/items")
@CrossOrigin({EnvConfig.DEV_BASE_URL, EnvConfig.PROD_BASE_URL})
public class CartItemController {
    private final UserDataMapper userDataMapper;
    private final CartItemService cartItemService;
    private final CartItemDtoMapper cartItemDtoMapper;

    public CartItemController(UserDataMapper userDataMapper, CartItemService cartItemService, CartItemDtoMapper cartItemDtoMapper) {
        this.userDataMapper = userDataMapper;
        this.cartItemService = cartItemService;
        this.cartItemDtoMapper = cartItemDtoMapper;
    }

    @PutMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putCartItem(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer pid, /*@Positive <-cannot see logging message*/ @PathVariable Integer quantity){
        FirebaseUserReqData firebaseUserReqData = userDataMapper.toFirebaseUserReqData(jwt);
        cartItemService.putCartItem(firebaseUserReqData, pid, quantity);
    }

    @GetMapping()
    public List<CartItemRespDto> getCartItem(@AuthenticationPrincipal Jwt jwt){
        FirebaseUserReqData firebaseUserReqData = userDataMapper.toFirebaseUserReqData(jwt);
        List<CartItemRespData> cartItemRespDataList = cartItemService.getCartItem(firebaseUserReqData);
        return cartItemDtoMapper.toCartItemRespDtoList(cartItemRespDataList);
    }

    @PatchMapping("/{pid}/{quantity}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCartQuantity(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer pid, @PathVariable Integer quantity){
        FirebaseUserReqData firebaseUserReqData = userDataMapper.toFirebaseUserReqData(jwt);
        cartItemService.updateCartQuantity(firebaseUserReqData, pid, quantity);
    }

    @DeleteMapping("/{pid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCartItem(@AuthenticationPrincipal Jwt jwt, @PathVariable Integer pid){
        FirebaseUserReqData firebaseUserReqData = userDataMapper.toFirebaseUserReqData(jwt);
        cartItemService.deleteCartItem(firebaseUserReqData, pid);
    }

}
