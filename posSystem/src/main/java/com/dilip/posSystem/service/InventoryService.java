package com.dilip.posSystem.service;

import com.dilip.posSystem.payload.dto.InventoryDto;

import java.util.List;

public interface InventoryService {

    InventoryDto createInventory(InventoryDto inventoryDto) throws Exception;
    InventoryDto updateInventory(Long id,InventoryDto inventoryDto) throws Exception;
    void deleteInventory(Long id) throws Exception;
    InventoryDto getInventoryById(Long id) throws Exception;
    InventoryDto getInventoryByProductIdAndStoreId(Long productId,Long storeId);
    List<InventoryDto> getInventoryByStoreId(Long StoreId);

}
