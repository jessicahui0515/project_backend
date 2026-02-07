package com.FSSE2510_project_jessica.FSSE2510_project.repository;

import com.FSSE2510_project_jessica.FSSE2510_project.data.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findById (Integer uid);

    List<ProductEntity> findAllByCategories_Category(String category);
}
