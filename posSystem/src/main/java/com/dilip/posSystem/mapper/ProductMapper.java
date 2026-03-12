package com.dilip.posSystem.mapper;

import com.dilip.posSystem.modal.Category;
import com.dilip.posSystem.modal.Product;
import com.dilip.posSystem.modal.Store;
import com.dilip.posSystem.payload.dto.ProductDto;

public class ProductMapper {
    public static ProductDto toDTO(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .brand(product.getBrand())
                .categoryId(product.getCategory() != null ? product.getCategory().getId() : null)
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .category(CategoryMapper.toDTO(product.getCategory()))
                .storeId(product.getStore() != null ? product.getStore().getId() : null)
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();

    }

    public static Product toEntity(ProductDto productDto, Store store, Category category) {
        return Product.builder()
                .name(productDto.getName())
                .store(store)
                .category(category)
                .sku(productDto.getSku())
                .brand(productDto.getBrand())
                .description(productDto.getDescription())
                .mrp(productDto.getMrp())
                .sellingPrice(productDto.getSellingPrice())
                .build();
    }
}
