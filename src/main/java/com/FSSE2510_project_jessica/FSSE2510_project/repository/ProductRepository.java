package com.FSSE2510_project_jessica.FSSE2510_project.repository;

import com.FSSE2510_project_jessica.FSSE2510_project.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {


    List<ProductEntity> findAllByCategories_Category(String category);
}
