package com.dilip.posSystem.payload.dto;

import com.dilip.posSystem.domain.PaymentType;
import com.dilip.posSystem.modal.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
    private Long id;
    private Double totalAmount;
    private LocalDateTime createdAt;
    private Long branchId;
    private Long CustomerId;
    private BranchDto branch;
    private UserDto cashier;
    private Customer customer;
    private PaymentType paymentType;
    private List<OrderItemDto> items;
}
