package com.dilip.posSystem.controller;

import com.dilip.posSystem.domain.StoreStatus;
import com.dilip.posSystem.exceptions.UserException;
import com.dilip.posSystem.mapper.StoreMapper;
import com.dilip.posSystem.modal.User;
import com.dilip.posSystem.payload.dto.StoreDto;
import com.dilip.posSystem.payload.response.ApiResponse;
import com.dilip.posSystem.service.StoreService;
import com.dilip.posSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {
     private final StoreService storeService;
     private final UserService userService;

     @PostMapping("")
     public ResponseEntity<StoreDto> createStore(
             @RequestBody StoreDto storeDto,
             @RequestHeader("Authorization") String jwt
     ) throws UserException {
         User user = userService.getUserFromJwtToken(jwt);
         return ResponseEntity.ok(storeService.createStore(storeDto,user));

     }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStore(
            @PathVariable Long id,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }

    @GetMapping("")
    public ResponseEntity<List<StoreDto>> getAllStore(
            @RequestHeader("Authorization") String jwt
    )  {
        return ResponseEntity.ok(storeService.getAllStores());
    }

    @GetMapping("/admin")
    public ResponseEntity<StoreDto> getStoreByAdmin(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {

        return ResponseEntity.ok(StoreMapper.toDTO(storeService.getStoreByAdmin()));

    }

    @GetMapping("/employee")
    public ResponseEntity<StoreDto> getStoreByEmployee(
            @RequestHeader("Authorization") String jwt
    ) throws UserException {

        return ResponseEntity.ok(storeService.getStoreByEmployee());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StoreDto> updateStore(
            @PathVariable Long id,
            @RequestBody StoreDto storeDto
    ) throws Exception {
         return ResponseEntity.ok(storeService.updateStore(id,storeDto));
    }

    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDto> moderateStore(
            @PathVariable Long id,
            @RequestParam StoreStatus status
            ) throws Exception {
        return ResponseEntity.ok(storeService.moderateStore(id,status));

    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteStore(
@PathVariable Long id
    ) throws Exception {

        storeService.deleteStore(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Successfully Deleted Store");
        return ResponseEntity.ok(apiResponse);
    }


}

