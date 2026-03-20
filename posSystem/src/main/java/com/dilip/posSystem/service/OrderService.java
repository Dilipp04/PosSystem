package com.dilip.posSystem.service;

import com.dilip.posSystem.domain.OrderStatus;
import com.dilip.posSystem.domain.PaymentType;
import com.dilip.posSystem.payload.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto) throws Exception;
    OrderDto getOrderById(Long id) throws Exception;
    List<OrderDto> getOrdersByBranch(Long branchId,
                                     Long customerId,
                                     Long cashierId,
                                     PaymentType paymentType,
                                     OrderStatus status);
    List<OrderDto> getOrderByCashier(Long cashierId);
    void deleteOrder(Long id) throws Exception;
    List<OrderDto> getTodayOrdersByBranch(Long branchId);
    List<OrderDto> getOrderByCustomerId(Long customerId);
    List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId);
}
