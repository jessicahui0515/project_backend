package com.FSSE2510_project_jessica.FSSE2510_project.service;

import com.FSSE2510_project_jessica.FSSE2510_project.data.product.domainObject.ProductRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.product.entity.ProductEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {
    List<ProductRespData> getAllProduct();

    ProductRespData getProductByPid(Integer pid);

    ProductEntity getEntityByPid(Integer pid);

    List<ProductRespData> getProductByCategory(String category);

    @Transactional
    void reduceStock(Integer pid, Integer quantity);
}
