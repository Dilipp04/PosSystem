package com.dilip.posSystem.controller;

import com.dilip.posSystem.domain.OrderStatus;
import com.dilip.posSystem.domain.PaymentType;
import com.dilip.posSystem.payload.dto.OrderDto;
import com.dilip.posSystem.payload.response.ApiResponse;
import com.dilip.posSystem.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws Exception {
        return  ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<OrderDto>> getOrderByStore(
            @PathVariable Long storeId,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long cashierId,
            @RequestParam(required = false) PaymentType paymentType,
            @RequestParam(required = false) OrderStatus status
            )  {
        return ResponseEntity.ok(orderService.getOrdersByStore(
                storeId,
                customerId,
                cashierId,
                paymentType,
                status
        ));
    }


    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<OrderDto>> getOrderByCashierId(
            @PathVariable Long cashierId
    )  {
        return ResponseEntity.ok(orderService.getOrderByCashier(cashierId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderDto>> getOrderByCustomerId(
            @PathVariable Long customerId
    )  {
        return ResponseEntity.ok(orderService.getOrderByCustomerId(customerId));
    }

    @GetMapping("/today/store/{storeId}")
    public ResponseEntity<List<OrderDto>> getTodayOrdersByStore(
            @PathVariable Long storeId
    )  {
        return ResponseEntity.ok(orderService.getTodayOrdersByStore(storeId));
    }

    @GetMapping("/recent/{storeId}")
    public ResponseEntity<List<OrderDto>> getRecentOrdersByStore(
            @PathVariable Long storeId
    )  {
        return ResponseEntity.ok(orderService.getTop5RecentOrdersByStoreId(storeId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteOrderById(
            @PathVariable Long id
    ) throws Exception {
        orderService.deleteOrder(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Order Deleted Successfully with ID : "+ id);
        return ResponseEntity.ok(apiResponse);
    }
}
