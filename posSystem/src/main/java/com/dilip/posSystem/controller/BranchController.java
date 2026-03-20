package com.dilip.posSystem.controller;

import com.dilip.posSystem.payload.dto.BranchDto;
import com.dilip.posSystem.payload.response.ApiResponse;
import com.dilip.posSystem.service.BranchService;
import com.dilip.posSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/branches")
public class BranchController {
    private final BranchService branchService;
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<BranchDto> createBranch(
            @RequestBody BranchDto branchDto) throws Exception {
        return ResponseEntity.ok(branchService.createBranch(branchDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> getBranch(
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchDto> updateBranch(
            @RequestBody BranchDto branchDto,
            @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(branchService.updateBranch(id, branchDto, null));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDto>> getBranchesByStoreId(
            @PathVariable Long storeId) throws Exception {
        return ResponseEntity.ok(branchService.getAllBranchesByStoreId(storeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranch(
            @PathVariable Long id) throws Exception {
        branchService.deleteBranch(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Branch Deleted Successfully");
        return ResponseEntity.ok(apiResponse);
    }

}
