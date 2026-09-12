package com.dilip.posSystem.service;

import com.dilip.posSystem.exceptions.UserException;
import com.dilip.posSystem.modal.Category;
import com.dilip.posSystem.payload.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO createCategory(CategoryDTO dto) throws Exception;

    List<CategoryDTO> getCategoriesByStore(Long storeId);

    CategoryDTO updateCategory(Long id, CategoryDTO dto) throws Exception;

    void deleteCategory(Long id) throws Exception;
}
