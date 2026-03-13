package com.dilip.posSystem.mapper;

import com.dilip.posSystem.modal.Branch;
import com.dilip.posSystem.modal.Store;
import com.dilip.posSystem.payload.dto.BranchDto;

import java.time.LocalDateTime;

public class BranchMapper {
    public static BranchDto toDTO(Branch branch){
       return BranchDto.builder()
               .id(branch.getId())
               .name(branch.getName())
               .address(branch.getAddress())
               .phone(branch.getPhone())
               .email(branch.getEmail())
               .workingDays(branch.getWorkingDays())
               .openTime(branch.getOpenTime())
               .closeTime(branch.getCloseTime())
               .createdAt(branch.getCreatedAt())
               .updatedAt(branch.getUpdatedAt())
               .storeId(branch.getStore()!=null?branch.getStore().getId():null)
               .build();
    }

    public static Branch toEntity(BranchDto branchDto, Store store){
        return Branch.builder()
                .id(branchDto.getId())
                .name(branchDto.getName())
                .address(branchDto.getAddress())
                .phone(branchDto.getPhone())
                .email(branchDto.getEmail())
                .workingDays(branchDto.getWorkingDays())
                .openTime(branchDto.getOpenTime())
                .closeTime(branchDto.getCloseTime())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .store(store)
                .build();
    }

}
