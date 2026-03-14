package com.dilip.posSystem.payload.dto;

 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryDto {
    private Long id;
    private BranchDto branch;
    private Long branchId;
    private ProductDto product;
    private Long productId;
    private Integer quantity;
    private LocalDateTime lastUpdated;
}
