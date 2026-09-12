package com.dilip.posSystem.payload.dto;

import com.dilip.posSystem.domain.PaymentType;
import com.dilip.posSystem.modal.Branch;
import com.dilip.posSystem.modal.Customer;
import com.dilip.posSystem.modal.OrderItem;
import com.dilip.posSystem.modal.User;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long id;

    private Double totalAmount;

    private LocalDateTime createdAt;

    private Long branchId;
    private Long customerId;

    private BranchDTO branch;

    private UserDto cashier;

    private Customer customer;

    private PaymentType paymentType;

    private List<OrderItemDTO> items;
}