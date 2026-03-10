package com.dilip.posSystem.controller;

import com.dilip.posSystem.modal.User;
import com.dilip.posSystem.payload.dto.ProductDto;
import com.dilip.posSystem.payload.response.ApiResponse;
import com.dilip.posSystem.service.ProductService;
import com.dilip.posSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
        private final ProductService productService;
        private final UserService userService;

        @PostMapping("")
        public ResponseEntity<ProductDto> createProduct(
                        @RequestBody ProductDto productDto,
                        @RequestHeader("Authorization") String jwt) throws Exception {
                User user = userService.getUserFromJwtToken(jwt);
                return ResponseEntity.ok(
                                productService.createProduct(
                                                productDto,
                                                user));
        }

        @GetMapping("/{id}")
        public ResponseEntity<ProductDto> getProductById(
                        @PathVariable Long id,
                        @RequestHeader("Authorization") String jwt) throws Exception {
                User user = userService.getUserFromJwtToken(jwt);
                return ResponseEntity.ok(
                                productService.getProduct(id, user));
        }

        @GetMapping("/store/{storeId}")
        public ResponseEntity<List<ProductDto>> getByStoreId(
                        @PathVariable Long storeId,
                        @RequestHeader("Authorization") String jwt) {
                return ResponseEntity.ok(
                                productService.getProductsByStoreId(storeId));
        }

        @GetMapping("/store/{storeId}/search")
        public ResponseEntity<List<ProductDto>> searchByKeyword(
                        @PathVariable Long storeId,
                        @RequestParam("keyword") String keyword,
                        @RequestHeader("Authorization") String jwt) {
                return ResponseEntity.ok(
                                productService.searchByKeyword(storeId, keyword));
        }

        @PatchMapping("/{id}")
        public ResponseEntity<ProductDto> updateProduct(
                        @PathVariable Long id,
                        @RequestBody ProductDto productDto,
                        @RequestHeader("Authorization") String jwt) throws Exception {
                User user = userService.getUserFromJwtToken(jwt);

                return ResponseEntity.ok(
                                productService.updateProduct(
                                                id,
                                                productDto,
                                                user));
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse> deleteProduct(
                        @PathVariable Long id,
                        @RequestHeader("Authorization") String jwt) throws Exception {
                User user = userService.getUserFromJwtToken(jwt);
                productService.deleteProduct(id, user);
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setMessage("Product Deleted Successfully");

                return ResponseEntity.ok(
                                apiResponse);
        }
}
