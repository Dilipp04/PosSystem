package com.dilip.posSystem.service;

import com.dilip.posSystem.payload.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto createCategory(CategoryDto dto) throws Exception;

    List<CategoryDto> getCategoryByStore(Long storeId);

    CategoryDto updateCategory(Long id, CategoryDto dto) throws Exception;

    void deleteCategory(Long id) throws Exception;
}
