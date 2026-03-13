package com.dilip.posSystem.repository;

import com.dilip.posSystem.modal.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<Branch,Long> {
    List<Branch> findByStoreId(Long storeId);
}
