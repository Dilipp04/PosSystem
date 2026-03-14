package com.dilip.posSystem.mapper;

import com.dilip.posSystem.modal.Branch;
import com.dilip.posSystem.modal.Inventory;
import com.dilip.posSystem.modal.Product;
import com.dilip.posSystem.payload.dto.InventoryDto;

public class InventoryMapper {
    public static InventoryDto toDTO(Inventory inventory){
        return InventoryDto.builder()
                .id(inventory.getId())
                .branchId(inventory.getBranch().getId())
                .productId(inventory.getProduct().getId())
                .product(ProductMapper.toDTO(inventory.getProduct()))
                .quantity(inventory.getQuantity())
                .lastUpdated(inventory.getLastUpdated())
                .build();

    }

    public static Inventory toEntity(
            InventoryDto inventoryDto,
            Branch branch,
            Product product
    ){
        return Inventory.builder()
                .branch(branch)
                .product(product)
                .quantity(inventoryDto.getQuantity())
                .build();
    }
}
