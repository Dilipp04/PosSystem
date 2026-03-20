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

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDto>> getOrderByBranch(
            @PathVariable Long branchId,
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long cashierId,
            @RequestParam(required = false) PaymentType paymentType,
            @RequestParam(required = false) OrderStatus status
            )  {
        return ResponseEntity.ok(orderService.getOrdersByBranch(
                branchId,
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

    @GetMapping("/today/branch/{branchId}")
    public ResponseEntity<List<OrderDto>> getTodayOrdersByBranch(
            @PathVariable Long branchId
    )  {
        return ResponseEntity.ok(orderService.getTodayOrdersByBranch(branchId));
    }

    @GetMapping("/recent/{branchId}")
    public ResponseEntity<List<OrderDto>> getRecentOrdersByBranch(
            @PathVariable Long branchId
    )  {
        return ResponseEntity.ok(orderService.getTop5RecentOrdersByBranchId(branchId));
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
