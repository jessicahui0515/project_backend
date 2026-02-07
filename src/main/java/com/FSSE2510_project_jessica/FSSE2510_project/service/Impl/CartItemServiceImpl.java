package com.FSSE2510_project_jessica.FSSE2510_project.service.Impl;

import com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.domainObject.CartItemRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.entity.CartItemEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.product.entity.ProductEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.domainObject.FirebaseUserReqData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.entity.UserEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.exception.CartItemNotEnoughStockException;
import com.FSSE2510_project_jessica.FSSE2510_project.exception.CartItemNotFoundException;
import com.FSSE2510_project_jessica.FSSE2510_project.exception.CartItemQuantityCannotBeNegativeException;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.cartItem.CartItemDataMapper;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.cartItem.CartItemEntityMapper;
import com.FSSE2510_project_jessica.FSSE2510_project.repository.CartItemRepository;
import com.FSSE2510_project_jessica.FSSE2510_project.service.CartItemService;
import com.FSSE2510_project_jessica.FSSE2510_project.service.ProductService;
import com.FSSE2510_project_jessica.FSSE2510_project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemServiceImpl implements CartItemService {
    private final Logger logger = LoggerFactory.getLogger(CartItemServiceImpl.class);
    public final UserService userService;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final CartItemEntityMapper cartItemEntityMapper;
    private final CartItemDataMapper cartItemDataMapper;

    public CartItemServiceImpl(UserService userService, CartItemRepository cartItemRepository, ProductService productService, CartItemEntityMapper cartItemEntityMapper, CartItemDataMapper cartItemDataMapper) {
        this.userService = userService;
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
        this.cartItemEntityMapper = cartItemEntityMapper;
        this.cartItemDataMapper = cartItemDataMapper;
    }

    @Override
    @Transactional //Since the quantity has been changed, @Transactional will do dirty check to update the changes in database
    public void putCartItem(FirebaseUserReqData userData, Integer pid, Integer quantity){
        try{
        UserEntity userEntity = userService.getUserEntityByEmail(userData);

        Optional <CartItemEntity> optionalCartItemEntity = cartItemRepository.findByProduct_PidAndUser_Uid(pid, userEntity.getUid());
        if (quantity<=0){
            throw new CartItemQuantityCannotBeNegativeException(quantity);
        }
        if (optionalCartItemEntity.isPresent()){
            CartItemEntity cartItemEntity = optionalCartItemEntity.get();
            if(cartItemEntity.getQuantity() + quantity > cartItemEntity.getProduct().getStock()){
                throw new CartItemNotEnoughStockException(quantity);
            }
            cartItemEntity.setQuantity(cartItemEntity.getQuantity()+quantity);

        } else {
            ProductEntity productEntity = productService.getEntityByPid(pid);
            if (quantity > productEntity.getStock()) {
                throw new CartItemNotEnoughStockException(quantity);
            }
            CartItemEntity cartItemEntity = cartItemEntityMapper.toCartItemEntity(userEntity, productEntity, quantity);
            cartItemRepository.save(cartItemEntity);
            }

        } catch (Exception e) {
            logger.warn("Put Item to cart failed:{}", e.getMessage());
            throw e;
        }
    }

    @Override
    public List<CartItemRespData> getCartItem(FirebaseUserReqData firebaseUserReqData){
        UserEntity userEntity = userService.getUserEntityByEmail(firebaseUserReqData);
        List<CartItemEntity> cartItemEntityList = cartItemRepository.findAllByUser(userEntity);
        return cartItemDataMapper.toCartItemRespDataList(cartItemEntityList);

    }

    @Transactional
    @Override
    public void updateCartQuantity(FirebaseUserReqData userData, Integer pid, Integer quantity){
        try {
            if (quantity<=0){
                throw new CartItemQuantityCannotBeNegativeException(quantity);
            }
            CartItemEntity cartItemEntity = getEntityByUidAndPid(userService.getUserEntityByEmail(userData).getUid(), pid);
            if (quantity > cartItemEntity.getProduct().getStock()){
                throw new CartItemNotEnoughStockException(quantity);
            }
            cartItemEntity.setQuantity(quantity);
        } catch (Exception e) {
            logger.warn("Update Item Quantity failed:{}", e.getMessage());
            throw e;
        }
    }

    @Override
    @Transactional
    public void deleteCartItem(FirebaseUserReqData userData, Integer pid){
        try {
            UserEntity userEntity = userService.getUserEntityByEmail(userData);
            getEntityByUidAndPid(userEntity.getUid(), pid);
            cartItemRepository.deleteByProduct_PidAndUser(pid, userEntity);
            } catch (Exception e) {
            logger.warn("Delete Cart Item failed:{}", e.getMessage());
            throw e;
        }
    }

    public CartItemEntity getEntityByUidAndPid (Integer uid, Integer pid){
        Optional<CartItemEntity> cartItemEntity = cartItemRepository.findByProduct_PidAndUser_Uid(pid,uid);
        if (cartItemEntity.isEmpty()){
            throw new CartItemNotFoundException(uid, pid);
        }
        return cartItemEntity.get();
    }

}
