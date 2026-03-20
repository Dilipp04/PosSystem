package com.dilip.posSystem.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemDto {
    private Long id;
    private Integer quantity;
    private Double price;
    private ProductDto product;
    private Long productId;
    private OrderDto order;
}
