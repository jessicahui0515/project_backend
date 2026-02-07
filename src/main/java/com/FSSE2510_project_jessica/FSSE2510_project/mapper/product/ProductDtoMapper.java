package com.FSSE2510_project_jessica.FSSE2510_project.mapper.product;

import com.FSSE2510_project_jessica.FSSE2510_project.data.product.Dto.GetAllProductRespDto;
import com.FSSE2510_project_jessica.FSSE2510_project.data.product.Dto.ProductByIdRespDto;
import com.FSSE2510_project_jessica.FSSE2510_project.data.product.domainObject.ProductRespData;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductDtoMapper {
    public GetAllProductRespDto toGetProductRespDto (ProductRespData respData){
        GetAllProductRespDto respDto = new GetAllProductRespDto();
        respDto.setPid(respData.getPid());
        respDto.setName(respData.getName());
        respDto.setDescription(respData.getDescription());
        respDto.setImageUrl(respData.getImageUrl());
        respDto.setImageUrls(respData.getImageUrls());
        respDto.setPrice(respData.getPrice());
        respDto.setHasStock(respData.getStock()>0);
        respDto.setBrand(respData.getBrand());
        respDto.setCategories(respData.getCategories());
        return respDto;
    }
    public List<GetAllProductRespDto> toGetProductRespDtoList (List<ProductRespData> ProductRespDataList){
        List<GetAllProductRespDto> getAllProductRespDtoList = new ArrayList<>();
        for (ProductRespData respData : ProductRespDataList){
            getAllProductRespDtoList.add(toGetProductRespDto(respData));
        }
        return getAllProductRespDtoList;
    }
    public ProductByIdRespDto toGetProductByIdRespDto (ProductRespData respData){
        ProductByIdRespDto respDto = new ProductByIdRespDto();
        respDto.setPid(respData.getPid());
        respDto.setName(respData.getName());
        respDto.setDescription(respData.getDescription());
        respDto.setImageUrl(respData.getImageUrl());
        respDto.setImageUrls(respData.getImageUrls());
        respDto.setPrice(respData.getPrice());
        respDto.setStock(respData.getStock());
        respDto.setBrand(respData.getBrand());
        respDto.setCategories(respData.getCategories());
        return respDto;
    }

}
