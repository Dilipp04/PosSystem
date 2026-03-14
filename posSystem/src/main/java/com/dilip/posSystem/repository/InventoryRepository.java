package com.dilip.posSystem.repository;

import com.dilip.posSystem.modal.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    List<Inventory> findByBranchId(Long branchId);
    Inventory findByProductIdAndBranchId(Long productId,Long branchId);
}
