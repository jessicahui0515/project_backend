package com.FSSE2510_project_jessica.FSSE2510_project.controller;

import com.FSSE2510_project_jessica.FSSE2510_project.config.EnvConfig;
import com.FSSE2510_project_jessica.FSSE2510_project.data.product.Dto.GetAllProductRespDto;
import com.FSSE2510_project_jessica.FSSE2510_project.data.product.Dto.ProductByIdRespDto;
import com.FSSE2510_project_jessica.FSSE2510_project.mapper.product.ProductDtoMapper;
import com.FSSE2510_project_jessica.FSSE2510_project.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/product")
@CrossOrigin({EnvConfig.DEV_BASE_URL, EnvConfig.PROD_BASE_URL})
public class ProductController {
    private final ProductService productService;
    private final ProductDtoMapper productDtoMapper;

    public ProductController(ProductService productService, ProductDtoMapper productDtoMapper) {
        this.productService = productService;
        this.productDtoMapper = productDtoMapper;
    }

    @GetMapping()
    public List<GetAllProductRespDto> getAllProduct(){
        return productDtoMapper.toGetProductRespDtoList(productService.getAllProduct());
    }
    @GetMapping("/{pid}")
    public ProductByIdRespDto getProductByPid(@PathVariable Integer pid ){
        return productDtoMapper.toGetProductByIdRespDto(productService.getProductByPid(pid));
    }
    @GetMapping("/category/{category}")
    public List<GetAllProductRespDto> getProductByCategory(@PathVariable String category){
        return productDtoMapper.toGetProductRespDtoList(productService.getProductByCategory(category));
    }

}
