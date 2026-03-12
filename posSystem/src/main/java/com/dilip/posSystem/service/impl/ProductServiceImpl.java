package com.dilip.posSystem.service.impl;

import com.dilip.posSystem.mapper.ProductMapper;
import com.dilip.posSystem.modal.Category;
import com.dilip.posSystem.modal.Product;
import com.dilip.posSystem.modal.Store;
import com.dilip.posSystem.modal.User;
import com.dilip.posSystem.payload.dto.ProductDto;
import com.dilip.posSystem.repository.CategoryRepository;
import com.dilip.posSystem.repository.ProductRepository;
import com.dilip.posSystem.repository.StoreRepository;
import com.dilip.posSystem.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws Exception {
        Store store = storeRepository.findById(
                productDto.getStoreId()
        ).orElseThrow(
                ()-> new Exception("Store Not found")
        );
        Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                ()->new Exception("Category not found")
        );
        Product product = ProductMapper.toEntity(productDto,store,category);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO( savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("Product not found")
        );
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
        product.setImage(productDto.getImage());
        product.setMrp(productDto.getMrp());
        product.setSellingPrice(productDto.getSellingPrice());
        product.setBrand(productDto.getBrand());
        product.setUpdatedAt(LocalDateTime.now());
        if(productDto.getCategoryId()!=null) {
            Category category = categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                    () -> new Exception("Category Not found")
            );

            product.setCategory(category);
        }

        return ProductMapper.toDTO( productRepository.save(product));
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("Product not found")
        );
       productRepository.delete(product);
    }

    @Override
    public ProductDto getProduct(Long id, User user) throws Exception {
        Product product = productRepository.findById(id).orElseThrow(
                ()-> new Exception("Product not found")
        );
        return ProductMapper.toDTO(product);
    }

    @Override
    public List<ProductDto> getProductsByStoreId(Long storeId) {
        List<Product> products =  productRepository.findByStoreId(storeId);
        return products.stream().map(ProductMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> searchByKeyword(Long storeId, String keyword) {

        List<Product> products =  productRepository.searchByKeyword(storeId,keyword);
        return products.stream().map(ProductMapper::toDTO).collect(Collectors.toList());

    }
}
