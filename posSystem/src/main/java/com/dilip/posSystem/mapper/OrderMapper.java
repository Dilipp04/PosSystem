package com.dilip.posSystem.mapper;

import com.dilip.posSystem.modal.Order;
import com.dilip.posSystem.payload.dto.OrderDto;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDto toDTO(Order order){

        return OrderDto.builder()
                .id(order.getId())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .branchId(order.getBranch().getId())
                .CustomerId(order.getCustomer().getId())
                .customer(order.getCustomer())
                .cashier(UserMapper.toDTO(order.getCashier()))
                .paymentType(order.getPaymentType())
                .items(order.getItems().stream().map(
                        OrderItemMapper::toDTO
                ).collect(Collectors.toList()))
                .build();
    }

}
