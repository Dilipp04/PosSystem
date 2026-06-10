package com.dilip.posSystem.mapper;

import com.dilip.posSystem.modal.Inventory;
import com.dilip.posSystem.modal.Product;
import com.dilip.posSystem.modal.Store;
import com.dilip.posSystem.payload.dto.InventoryDto;

public class InventoryMapper {
    public static InventoryDto toDTO(Inventory inventory){
        return InventoryDto.builder()
                .id(inventory.getId())
                .storeId(inventory.getStore().getId())
                .productId(inventory.getProduct().getId())
                .product(ProductMapper.toDTO(inventory.getProduct()))
                .quantity(inventory.getQuantity())
                .lastUpdated(inventory.getLastUpdated())
                .build();

    }

    public static Inventory toEntity(
            InventoryDto inventoryDto,
            Store store,
            Product product
    ){
        return Inventory.builder()
                .store(store)
                .product(product)
                .quantity(inventoryDto.getQuantity())
                .build();
    }
}
