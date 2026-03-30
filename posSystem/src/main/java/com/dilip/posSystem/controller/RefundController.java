package com.dilip.posSystem.controller;

import com.dilip.posSystem.payload.dto.RefundDto;
import com.dilip.posSystem.payload.response.ApiResponse;
import com.dilip.posSystem.service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/refunds")
@RequiredArgsConstructor
public class RefundController {

    private final RefundService refundService;

    @PostMapping
    public ResponseEntity<RefundDto> createRefund(@RequestBody RefundDto refundDto) throws Exception {
        RefundDto createdRefund = refundService.createRefund(refundDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRefund);
    }

    @GetMapping
    public ResponseEntity<List<RefundDto>> getAllRefunds() {
        return ResponseEntity.ok(refundService.getAllRefunds());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RefundDto> getRefundById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(refundService.getRefundById(id));
    }

    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<RefundDto>> getRefundByCashier(@PathVariable Long cashierId) {
        return ResponseEntity.ok(refundService.getRefundByCashier(cashierId));
    }

    @GetMapping("/cashier/{cashierId}/range")
    public ResponseEntity<List<RefundDto>> getRefundByCashierAndDateRange(
            @PathVariable Long cashierId,
            @RequestParam  LocalDateTime from,
            @RequestParam  LocalDateTime to) {
        return ResponseEntity.ok(refundService.getRefundByCashierAndDateRange(cashierId, from, to));
    }

    @GetMapping("/shift/{shiftReportId}")
    public ResponseEntity<List<RefundDto>> getRefundByShiftReport(@PathVariable Long shiftReportId) {
        return ResponseEntity.ok(refundService.getRefundByShiftReport(shiftReportId));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<RefundDto>> getRefundByBranch(@PathVariable Long branchId) {
        return ResponseEntity.ok(refundService.getRefundByBranchId(branchId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRefund(@PathVariable Long id) throws Exception {
        refundService.deleteRefund(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Refund is deleted Successfully");
        return ResponseEntity.ok(apiResponse);
    }
}