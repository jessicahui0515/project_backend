package com.FSSE2510_project_jessica.FSSE2510_project.data.transactionProduct;

import com.FSSE2510_project_jessica.FSSE2510_project.data.product.entity.ProductEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class TransactionProductRespDto {
    private Integer tpid;
    private ProductEntity product;
    private Integer quantity;
    private BigDecimal subTotal;
}
