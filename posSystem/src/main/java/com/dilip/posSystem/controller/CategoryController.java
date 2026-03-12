package com.dilip.posSystem.controller;

import com.dilip.posSystem.payload.dto.CategoryDto;
import com.dilip.posSystem.payload.response.ApiResponse;
import com.dilip.posSystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("")
    public ResponseEntity<CategoryDto> createCategory(
            @RequestBody CategoryDto categoryDto) throws Exception {

        return ResponseEntity.ok(categoryService.createCategory(categoryDto));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoryDto>> getCategory(
            @PathVariable Long storeId) {
        return ResponseEntity.ok(categoryService.getCategoryByStore(storeId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> createCategory(
            @RequestBody CategoryDto categoryDto,
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(categoryService.updateCategory(id, categoryDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(
            @PathVariable Long id) throws Exception {
        categoryService.deleteCategory(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Category Deleted Successfully");

        return ResponseEntity.ok(apiResponse);
    }

}
