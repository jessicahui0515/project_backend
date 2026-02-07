package com.FSSE2510_project_jessica.FSSE2510_project.mapper.product;

import com.FSSE2510_project_jessica.FSSE2510_project.data.product.domainObject.ProductRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.product.entity.CategoryEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.product.entity.ProductEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.data.product.entity.ProductImageEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDataMapper {
    public ProductRespData toProductRespData (ProductEntity productEntity){
        ProductRespData respData = new ProductRespData();
        respData.setPid(productEntity.getPid());
        respData.setName(productEntity.getName());
        respData.setDescription(productEntity.getDescription());
        respData.setImageUrl(productEntity.getImageUrl());
        respData.setImageUrls(productEntity.getImageUrls().stream().map(ProductImageEntity::getImageUrl) .collect(Collectors.toList()));
        respData.setPrice(productEntity.getPrice());
        respData.setStock(productEntity.getStock());
        respData.setBrand(productEntity.getBrand());
        respData.setCategories(productEntity.getCategories().stream().map(CategoryEntity::getCategory) .collect(Collectors.toList()));
        return respData;
    }
    public List<ProductRespData> toProductRespDataList (Iterable<ProductEntity>productEntityIterable){
        List<ProductRespData> productRespDataList = new ArrayList<>();
        for (ProductEntity productEntity : productEntityIterable){
            productRespDataList.add(toProductRespData(productEntity));
        }
        return productRespDataList;
    }
}

