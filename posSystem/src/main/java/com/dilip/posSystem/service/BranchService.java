package com.dilip.posSystem.service;

import com.dilip.posSystem.modal.User;
import com.dilip.posSystem.payload.dto.BranchDto;

import java.util.List;

public interface BranchService {
    BranchDto createBranch(BranchDto branchDto) throws Exception;

    BranchDto updateBranch(Long id, BranchDto branchDto, User user) throws Exception;

    void deleteBranch(Long id) throws Exception;

    List<BranchDto> getAllBranchesByStoreId(Long storeId);

    BranchDto getBranchById(Long id) throws Exception;
}
