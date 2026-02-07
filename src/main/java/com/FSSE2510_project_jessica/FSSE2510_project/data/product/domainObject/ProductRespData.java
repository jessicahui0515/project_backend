package com.FSSE2510_project_jessica.FSSE2510_project.data.product.domainObject;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
public class ProductRespData {
    private Integer pid;
    private String name;
    private String description;
    private String imageUrl;
    private List<String> imageUrls;
    private BigDecimal price;
    private Integer stock;
    private String brand;
    private List<String> categories;


}
