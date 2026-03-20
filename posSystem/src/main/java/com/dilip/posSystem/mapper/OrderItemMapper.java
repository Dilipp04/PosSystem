package com.dilip.posSystem.mapper;

import com.dilip.posSystem.modal.OrderItem;
import com.dilip.posSystem.payload.dto.OrderItemDto;

public class OrderItemMapper {
    public static OrderItemDto toDTO(OrderItem orderItem){
        if(orderItem==null)return null;
        return OrderItemDto.builder()
                .id(orderItem.getId())
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .productId(orderItem.getProduct().getId())
                .product(ProductMapper.toDTO(orderItem.getProduct()))
                .build();
    }
}
