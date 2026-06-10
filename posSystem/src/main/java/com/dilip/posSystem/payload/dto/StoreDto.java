package com.dilip.posSystem.payload.dto;

import com.dilip.posSystem.domain.StoreStatus;
import com.dilip.posSystem.modal.StoreContact;
import lombok.Data;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class StoreDto {

    private Long id;

    private String brand;

    private UserDto admin;

    private String address;

    private String phone;

    private String email;

    private List<String> workingDays;

    private LocalTime openTime;

    private LocalTime closeTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;
    private StoreStatus status;

    private StoreContact contact ;


}
