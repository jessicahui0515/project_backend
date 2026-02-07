package com.FSSE2510_project_jessica.FSSE2510_project.data.cartItem.entity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.product.entity.ProductEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="cart_item")
public class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;

    @ManyToOne
    @JoinColumn(name = "pid",nullable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "uid",nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private Integer quantity;
}
