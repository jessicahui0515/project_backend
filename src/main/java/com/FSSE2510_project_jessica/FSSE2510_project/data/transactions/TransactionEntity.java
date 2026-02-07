package com.FSSE2510_project_jessica.FSSE2510_project.data.transactions;

import com.FSSE2510_project_jessica.FSSE2510_project.data.transactionProduct.TransactionProductEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter
@Table(name ="transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tid;

    @ManyToOne
    @JoinColumn(name = "buyer_uid",nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private BigDecimal total;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionProductEntity> productEntityList;


}
