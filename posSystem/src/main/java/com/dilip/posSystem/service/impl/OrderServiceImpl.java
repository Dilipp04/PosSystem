package com.dilip.posSystem.service.impl;

import com.dilip.posSystem.domain.OrderStatus;
import com.dilip.posSystem.domain.PaymentType;
import com.dilip.posSystem.mapper.OrderMapper;
import com.dilip.posSystem.modal.*;
import com.dilip.posSystem.payload.dto.OrderDto;
import com.dilip.posSystem.repository.OrderRepository;
import com.dilip.posSystem.repository.ProductRepository;
import com.dilip.posSystem.service.OrderService;
import com.dilip.posSystem.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
        private final OrderRepository orderRepository;
        private final UserService userService;
        private final ProductRepository productRepository;

        @Override
        public OrderDto createOrder(OrderDto orderDto) throws Exception {
                User cashier = userService.getCurrentUser();
                Store store = cashier.getStore();
                if (store == null) {
                        throw new Exception("Store not found");
                }
                Order order = Order.builder()
                                .totalAmount(orderDto.getTotalAmount())
                                .createdAt(LocalDateTime.now())
                                .store(store)
                                .cashier(cashier)
                                .customer(orderDto.getCustomer())
                                .paymentType(orderDto.getPaymentType())
                                .build();
                List<OrderItem> orderItems = orderDto.getItems().stream().map(
                                itemDto -> {
                                        Product product = productRepository.findById(itemDto.getProductId())
                                                        .orElseThrow(
                                                                        () -> new EntityNotFoundException(
                                                                                        "Product not Found"));
                                        return OrderItem.builder()
                                                        .product(product)
                                                        .quantity(itemDto.getQuantity())
                                                        .price(product.getSellingPrice() * itemDto.getQuantity())

                                                        .order(order)
                                                        .build();
                                }).toList();
                double total = orderItems.stream().mapToDouble(
                                OrderItem::getPrice).sum();
                order.setTotalAmount(total);
                order.setItems(orderItems);
                Order savedOrder = orderRepository.save(order);
                return OrderMapper.toDTO(savedOrder);
        }

        @Override
        public OrderDto getOrderById(Long id) throws Exception {

                return OrderMapper.toDTO(orderRepository.findById(id).orElseThrow(
                                () -> new Exception("Order not found with id" + id)));
        }

        @Override
        public List<OrderDto> getOrdersByStore(Long storeId, Long customerId, Long cashierId, PaymentType paymentType,
                        OrderStatus status) {
                return orderRepository.findByStoreId(storeId)
                                .stream()
                                .filter(
                                                order -> customerId == null || (order.getCustomer() != null
                                                                && order.getCustomer().getId().equals(customerId)))
                                .filter(
                                                order -> cashierId == null || order.getCashier() != null
                                                                && order.getCashier().getId().equals(cashierId))
                                .filter(
                                                order -> paymentType == null || order.getPaymentType() == paymentType)
                                .map(OrderMapper::toDTO).collect(Collectors.toList());
        }

        @Override
        public List<OrderDto> getOrderByCashier(Long cashierId) {
                return orderRepository.findByCashierId(cashierId)
                                .stream().map(OrderMapper::toDTO).collect(Collectors.toList());
        }

        @Override
        public void deleteOrder(Long id) throws Exception {
                Order order = orderRepository.findById(id).orElseThrow(
                                () -> new Exception("Order not found"));
                orderRepository.delete(order);
        }

        @Override
        public List<OrderDto> getTodayOrdersByStore(Long storeId) {
                LocalDate today = LocalDate.now();
                LocalDateTime start = today.atStartOfDay();
                LocalDateTime end = today.plusDays(1).atStartOfDay();

                return orderRepository.findByStoreIdAndCreatedAtBetween(storeId, start, end)
                                .stream().map(
                                                OrderMapper::toDTO)
                                .collect(Collectors.toList());
        }

        @Override
        public List<OrderDto> getOrderByCustomerId(Long customerId) {

                return orderRepository.findByCustomerId(customerId).stream().map(
                                OrderMapper::toDTO).collect(Collectors.toList());
        }

        @Override
        public List<OrderDto> getTop5RecentOrdersByStoreId(Long storeId) {
                return orderRepository.findTop5ByStoreIdOrderByCreatedAtDesc(storeId)
                                .stream()
                                .map(OrderMapper::toDTO)
                                .collect(Collectors.toList());
        }
}
