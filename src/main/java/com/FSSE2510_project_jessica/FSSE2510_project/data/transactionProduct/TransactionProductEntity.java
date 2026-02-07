package com.FSSE2510_project_jessica.FSSE2510_project.data.transactionProduct;

import com.FSSE2510_project_jessica.FSSE2510_project.data.product.entity.ProductEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.transactions.TransactionEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@Table (name = "transactionProduct")
public class TransactionProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tpid;

    @ManyToOne
    @JoinColumn(name = "tid", nullable = false)
    private TransactionEntity transaction;

    @ManyToOne
    private ProductEntity product;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false, precision = 10, scale =2)
    private BigDecimal subTotal;


}
