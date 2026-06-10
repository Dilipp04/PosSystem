package com.dilip.posSystem.service.impl;

import com.dilip.posSystem.mapper.InventoryMapper;
import com.dilip.posSystem.modal.Inventory;
import com.dilip.posSystem.modal.Product;
import com.dilip.posSystem.modal.Store;
import com.dilip.posSystem.payload.dto.InventoryDto;
import com.dilip.posSystem.repository.InventoryRepository;
import com.dilip.posSystem.repository.ProductRepository;
import com.dilip.posSystem.repository.StoreRepository;
import com.dilip.posSystem.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) throws Exception {
        Store store = storeRepository.findById(inventoryDto.getStoreId()).orElseThrow(
                ()-> new Exception("store Not Found")
        );
        Product product = productRepository.findById(inventoryDto.getProductId()).orElseThrow(
                ()->new Exception("Product Not found")
        );
        Inventory savedInventory = InventoryMapper.toEntity(inventoryDto,store,product);
        return InventoryMapper.toDTO(inventoryRepository.save(savedInventory));
    }

    @Override
    public InventoryDto updateInventory(Long id,InventoryDto inventoryDto) throws Exception {
        Inventory existing = inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Inventory Not Found")
        );
        existing.setQuantity(inventoryDto.getQuantity());
        return InventoryMapper.toDTO(inventoryRepository.save(existing));
    }

    @Override
    public void deleteInventory(Long id) throws Exception {
        Inventory existing = inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Inventory Not Found")
        );
        inventoryRepository.delete(existing);
    }

    @Override
    public InventoryDto getInventoryById(Long id) throws Exception {
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(
                ()-> new Exception("Inventory Not Found")
        );
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public InventoryDto getInventoryByProductIdAndStoreId(Long productId, Long storeId) {

        Inventory inventory = inventoryRepository.findByProductIdAndStoreId(productId,storeId);
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public List<InventoryDto> getInventoryByStoreId(Long storeId) {
        List<Inventory> inventories = inventoryRepository.findByStoreId(storeId);
        return inventories.stream().map(InventoryMapper::toDTO).collect(Collectors.toList());
    }
}
