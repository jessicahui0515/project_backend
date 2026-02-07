package com.FSSE2510_project_jessica.FSSE2510_project.mapper.transactionProduct;

import com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.entity.CartItemEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactionProduct.TransactionProductEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class TransactionProductEntityMapper {
    public List<TransactionProductEntity> toTransactionProductEntity(List<CartItemEntity> cartItemEntityList, TransactionEntity transaction) {
        List<TransactionProductEntity> productEntityList = new ArrayList<>();
        for (CartItemEntity cartItemEntity : cartItemEntityList) {
            TransactionProductEntity tpe = new TransactionProductEntity();
            tpe.setTransaction(transaction);
            tpe.setProduct(cartItemEntity.getProduct());
            tpe.setQuantity(cartItemEntity.getQuantity());
            BigDecimal subTotal = cartItemEntity.getProduct().getPrice().multiply(BigDecimal.valueOf(cartItemEntity.getQuantity()));
            tpe.setSubTotal(subTotal);
            productEntityList.add(tpe);
        }
        return productEntityList;
    }
}
