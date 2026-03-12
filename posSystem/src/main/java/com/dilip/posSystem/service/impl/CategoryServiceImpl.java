package com.dilip.posSystem.service.impl;

import com.dilip.posSystem.domain.UserRole;
import com.dilip.posSystem.mapper.CategoryMapper;
import com.dilip.posSystem.modal.Category;
import com.dilip.posSystem.modal.Store;
import com.dilip.posSystem.modal.User;
import com.dilip.posSystem.payload.dto.CategoryDto;
import com.dilip.posSystem.repository.CategoryRepository;
import com.dilip.posSystem.repository.StoreRepository;
import com.dilip.posSystem.service.CategoryService;
import com.dilip.posSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;

    @Override
    public CategoryDto createCategory(CategoryDto dto) throws Exception {
        User user = userService.getCurrentUser();
        Store store = storeRepository.findById(dto.getStoreId()).orElseThrow(
                () -> new Exception("Store not found"));
        Category category = Category.builder()

                .name(dto.getName())
                .store(store)
                .build();

        checkAuthority(user, category.getStore());

        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getCategoryByStore(Long storeId) {
        List<Category> categories = categoryRepository.findByStoreId(storeId);
        return categories.stream().map(CategoryMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto dto) throws Exception {
        User user = userService.getCurrentUser();
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new Exception("Category not found"));
        checkAuthority(user, category.getStore());
        category.setName(dto.getName());

        return CategoryMapper.toDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category = categoryRepository.findById(id).orElseThrow(
                () -> new Exception("Category not found"));
        User user = userService.getCurrentUser();
        checkAuthority(user, category.getStore());
        categoryRepository.delete(category);
    }

    private void checkAuthority(User user, Store store) throws Exception {
        boolean isAdmin = user.getRole().equals(UserRole.ROLE_STORE_ADMIN);
        boolean isManager = user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore = user.equals(store.getAdmin());

        if (!(isAdmin && isSameStore) && !isManager) {
            throw new Exception("You don't have permission to manage this category");

        }

    }
}
