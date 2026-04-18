package com.dilip.posSystem.payload.dto;

import com.dilip.posSystem.modal.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShiftReportDto {
    private Long id;

    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;

    private Double totalSales = 0.0;
    private Double totalRefunds = 0.0;
    private Double netSales = 0.0;
    private int totalOrders = 0;

    private UserDto cashier;
    private Long cashierId;

    private BranchDto branch;
    private Long branchId;

    private List<PaymentSummary> paymentSummaries;

    private List<ProductDto> topSellingProducts;

    private List<OrderDto> recentOrders;

    private List<RefundDto> refunds;
}
