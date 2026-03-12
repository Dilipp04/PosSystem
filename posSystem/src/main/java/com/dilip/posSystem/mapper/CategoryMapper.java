package com.dilip.posSystem.mapper;

import com.dilip.posSystem.modal.Category;
import com.dilip.posSystem.payload.dto.CategoryDto;

public class CategoryMapper {
    public static CategoryDto toDTO(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .storeId(category.getStore() != null ? category.getStore().getId() : null)
                .build();
    }
}
