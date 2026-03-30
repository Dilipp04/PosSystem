package com.dilip.posSystem.payload.dto;

import com.dilip.posSystem.domain.PaymentType;
import com.dilip.posSystem.modal.Branch;

import com.dilip.posSystem.modal.ShiftReport;
import com.dilip.posSystem.modal.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefundDto {
    private Long id;

    private OrderDto order;
    private Long orderId;

    private String reason;

    private Double amount;

//    private ShiftReport shiftReport;
    private Long shiftReportId;

    private User cashier;
    private String cashierName;

    private Branch branch;
    private Long branchId;

    private LocalDateTime createdAt;
    private PaymentType paymentType;
}
