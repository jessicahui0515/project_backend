package com.FSSE2510_project_jessica.FSSE2510_project.service.Impl;

import com.FSSE2510_project_jessica.FSSE2510_project.exception.CartItemNotEnoughStockException;
import com.FSSE2510_project_jessica.FSSE2510_project.exception.CategoryNotFoundException;
import com.FSSE2510_project_jessica.FSSE2510_project.repository.ProductRepository;
import com.FSSE2510_project_jessica.FSSE2510_project.data.product.domainObject.ProductRespData;
import com.FSSE2510_project_jessica.FSSE2510_project.data.product.entity.ProductEntity;
import com.FSSE2510_project_jessica.FSSE2510_project.exception.PidNotFoundException;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.product.ProductDataMapper;
import com.FSSE2510_project_jessica.FSSE2510_project.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;
    private final ProductDataMapper productDataMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductDataMapper productDataMapper){
        this.productRepository = productRepository;
        this.productDataMapper = productDataMapper;
    }
    @Override
    public List<ProductRespData> getAllProduct(){
        Iterable<ProductEntity> productEntityIterable = productRepository.findAll();
        List<ProductRespData> productRespDataList = productDataMapper.toProductRespDataList(productEntityIterable);
        return productRespDataList;
    }
    @Override
    public ProductRespData getProductByPid(Integer pid){
        try {
            ProductEntity productEntity = getEntityByPid(pid);
            ProductRespData respData = productDataMapper.toProductRespData(productEntity);

            return respData;
        } catch (Exception e) {
            logger.warn("Get Product failed:{}", e.getMessage());
            throw e;
        }
    }
    @Override
    public ProductEntity getEntityByPid(Integer pid){
        Optional<ProductEntity> productEntity = productRepository.findById(pid);
        if(productEntity.isEmpty()){
            throw new PidNotFoundException(pid);
        }
        return productEntity.get();
    }

    @Override
    public List<ProductRespData> getProductByCategory(String category){
        List<ProductEntity> productEntities = productRepository.findAllByCategories_Category(category);

        if(productEntities.isEmpty()){
            throw new CategoryNotFoundException(category);
        }
        return productDataMapper.toProductRespDataList(productEntities);
    }

    @Transactional
    @Override
    public void reduceStock(Integer pid, Integer quantity){
        ProductEntity productEntity = getEntityByPid(pid);
        if (productEntity.getStock() < quantity){
            throw new CartItemNotEnoughStockException(quantity);
        }
        productEntity.setStock(productEntity.getStock() - quantity);
    }
}
