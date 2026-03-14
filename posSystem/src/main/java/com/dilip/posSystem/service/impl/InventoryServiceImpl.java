package com.dilip.posSystem.service.impl;

import com.dilip.posSystem.mapper.InventoryMapper;
import com.dilip.posSystem.modal.Branch;
import com.dilip.posSystem.modal.Inventory;
import com.dilip.posSystem.modal.Product;
import com.dilip.posSystem.payload.dto.InventoryDto;
import com.dilip.posSystem.repository.BranchRepository;
import com.dilip.posSystem.repository.InventoryRepository;
import com.dilip.posSystem.repository.ProductRepository;
import com.dilip.posSystem.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final BranchRepository branchRepository;
    private final ProductRepository productRepository;

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) throws Exception {
        Branch branch = branchRepository.findById(inventoryDto.getBranchId()).orElseThrow(
                ()-> new Exception("Branch Not Found")
        );
        Product product = productRepository.findById(inventoryDto.getProductId()).orElseThrow(
                ()->new Exception("Product Not found")
        );
        Inventory savedInventory = InventoryMapper.toEntity(inventoryDto,branch,product);
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
    public InventoryDto getInventoryByProductIdAndBranchId(Long productId, Long branchId) {

        Inventory inventory = inventoryRepository.findByProductIdAndBranchId(productId,branchId);
        return InventoryMapper.toDTO(inventory);
    }

    @Override
    public List<InventoryDto> getInventoryByBranchId(Long branchId) {
        List<Inventory> inventories = inventoryRepository.findByBranchId(branchId);
        return inventories.stream().map(InventoryMapper::toDTO).collect(Collectors.toList());
    }
}
