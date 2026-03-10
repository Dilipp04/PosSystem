package com.dilip.posSystem.mapper;

import com.dilip.posSystem.modal.Product;
import com.dilip.posSystem.modal.Store;
import com.dilip.posSystem.payload.dto.ProductDto;

public class ProductMapper {
    public static ProductDto toDTO(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .brand(product.getBrand())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .storeId(product.getStore()!=null?product.getStore().getId():null)
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
//                .categoryId(product.)
    }

    public static Product toEntity(ProductDto productDto, Store store){
        return Product.builder()
                .name(productDto.getName())
                .sku(productDto.getSku())
                .brand(productDto.getBrand())
                .description(productDto.getDescription())
                .mrp(productDto.getMrp())
                .sellingPrice(productDto.getSellingPrice())
                .build();
    }
}
