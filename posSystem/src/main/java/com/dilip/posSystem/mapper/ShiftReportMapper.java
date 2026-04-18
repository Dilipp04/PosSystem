package com.dilip.posSystem.mapper;


import com.dilip.posSystem.modal.Order;
import com.dilip.posSystem.modal.Product;
import com.dilip.posSystem.modal.Refund;
import com.dilip.posSystem.modal.ShiftReport;
import com.dilip.posSystem.payload.dto.OrderDto;
import com.dilip.posSystem.payload.dto.ProductDto;
import com.dilip.posSystem.payload.dto.RefundDto;
import com.dilip.posSystem.payload.dto.ShiftReportDto;

import java.util.List;
import java.util.stream.Collectors;

public class ShiftReportMapper {
    public static ShiftReportDto toDTO(ShiftReport entity){
        return ShiftReportDto.builder()
                .id(entity.getId())
                .shiftEnd(entity.getShiftEnd())
                .shiftStart(entity.getShiftStart())
                .totalSales(entity.getTotalSales())
                .totalOrders(entity.getTotalOrders())
                .totalRefunds(entity.getTotalRefunds())
                .netSales(entity.getNetSales())
                .cashier(UserMapper.toDTO(entity.getCashier()))
                .cashierId(entity.getCashier().getId())
                .branchId(entity.getBranch().getId())
                .recentOrders(mapOrders(entity.getRecentOrders()))
                .topSellingProducts(mapProducts(entity.getTopSellingProducts()))
                .refunds(mapRefunds(entity.getRefunds()))
                .paymentSummaries(entity.getPaymentSummaries())
                .build();
    }

    private static List<RefundDto> mapRefunds(List<Refund> refunds) {
        if(refunds == null || refunds.isEmpty()){return null;}

        return refunds.stream().map(RefundMapper::toDTO).collect(Collectors.toList());

    }

    private static List<OrderDto> mapOrders(List<Order> recentOrders) {
        if(recentOrders == null || recentOrders.isEmpty()){return null;}

        return recentOrders.stream().map(OrderMapper::toDTO).collect(Collectors.toList());

    }

    private static List<ProductDto> mapProducts(List<Product> topSellingProducts) {
        if(topSellingProducts == null || topSellingProducts.isEmpty()){return null;}

        return topSellingProducts.stream().map(ProductMapper::toDTO).collect(Collectors.toList());

    }
}
