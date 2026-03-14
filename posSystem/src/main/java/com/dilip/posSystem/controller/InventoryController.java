package com.dilip.posSystem.controller;

import com.dilip.posSystem.payload.dto.InventoryDto;
import com.dilip.posSystem.payload.response.ApiResponse;
import com.dilip.posSystem.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventories")
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("")
    public ResponseEntity<InventoryDto> createInventory(
            @RequestBody InventoryDto inventoryDto
    ) throws Exception {
        return ResponseEntity.ok(inventoryService.createInventory(inventoryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> updateInventory(
            @PathVariable Long id,
            @RequestBody InventoryDto inventoryDto
    ) throws Exception {
        return ResponseEntity.ok(inventoryService.updateInventory(id,inventoryDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryDto> getInventoryById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteInventory(
            @PathVariable Long id
    ) throws Exception {
        inventoryService.deleteInventory(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Inventory Deleted Successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<InventoryDto>> getInventoryByBranch(
            @PathVariable Long branchId
    ){
        return ResponseEntity.ok(inventoryService.getInventoryByBranchId(branchId));
    }

    @GetMapping("/branch/{branchId}/product/{productId}")
    public ResponseEntity<InventoryDto> getInventoryByProductAndBranch(
            @PathVariable Long productId,
            @PathVariable Long branchId
    )  {
        return ResponseEntity.ok(inventoryService.getInventoryByProductIdAndBranchId(productId,branchId));
    }
}
