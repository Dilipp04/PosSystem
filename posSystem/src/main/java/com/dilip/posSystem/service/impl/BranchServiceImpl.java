package com.dilip.posSystem.service.impl;

import com.dilip.posSystem.exceptions.UserException;
import com.dilip.posSystem.mapper.BranchMapper;
import com.dilip.posSystem.modal.Branch;
import com.dilip.posSystem.modal.Store;
import com.dilip.posSystem.modal.User;
import com.dilip.posSystem.payload.dto.BranchDto;
import com.dilip.posSystem.repository.BranchRepository;
import com.dilip.posSystem.repository.StoreRepository;
import com.dilip.posSystem.service.BranchService;
import com.dilip.posSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private final UserService userService;

    @Override
    public BranchDto createBranch(BranchDto branchDto) throws UserException {

        User currentUser = userService.getCurrentUser();
        Store store = storeRepository.findByAdminId(currentUser.getId());
        Branch savedBranch = BranchMapper.toEntity(branchDto,store);
        return BranchMapper.toDTO(branchRepository.save(savedBranch));
    }

    @Override
    public BranchDto updateBranch(Long id, BranchDto branchDto, User user) throws Exception {

         Branch existing = branchRepository.findById(id).orElseThrow(
                 ()->new Exception("Branch Not Exist")
         );

         existing.setName(branchDto.getName());
         existing.setWorkingDays(branchDto.getWorkingDays());
         existing.setEmail(branchDto.getEmail());
         existing.setPhone(branchDto.getPhone());
         existing.setAddress(branchDto.getAddress());
         existing.setOpenTime(branchDto.getOpenTime());
         existing.setCloseTime(branchDto.getCloseTime());
         existing.setUpdatedAt(LocalDateTime.now());
         Branch  updatedBranch= branchRepository.save(existing);
         return BranchMapper.toDTO(updatedBranch);
    }

    @Override
    public void deleteBranch(Long id) throws Exception {
        Branch branch = branchRepository.findById(id).orElseThrow(
                ()-> new Exception("Branch Not Exist")
        );
        branchRepository.delete(branch);

    }

    @Override
    public List<BranchDto> getAllBranchesByStoreId(Long storeId) {
       List<Branch> branches =  branchRepository.findByStoreId(storeId);
        return branches.stream().map(BranchMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public BranchDto getBranchById(Long id) throws Exception {
        Branch branch = branchRepository.findById(id).orElseThrow(
                ()-> new Exception("Branch Not Exist")
        );
         return BranchMapper.toDTO(branch);

    }
}
