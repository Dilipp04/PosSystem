package com.dilip.posSystem.payload.dto;

import com.dilip.posSystem.domain.StoreStatus;
import com.dilip.posSystem.modal.StoreContact;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StoreDto {

    private Long id;

    private String brand;

    private UserDto admin;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;
    private StoreStatus status;

    private StoreContact contact ;


}
