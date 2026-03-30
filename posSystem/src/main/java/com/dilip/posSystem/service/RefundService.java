package com.dilip.posSystem.service;

import com.dilip.posSystem.exceptions.UserException;
import com.dilip.posSystem.modal.Refund;
import com.dilip.posSystem.payload.dto.RefundDto;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundService {
    RefundDto createRefund(RefundDto refundDto) throws Exception;
    List<RefundDto> getAllRefunds();
    List<RefundDto> getRefundByCashier(Long cashierId);
    List<RefundDto> getRefundByShiftReport(Long shiftReportId);
    List<RefundDto> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate , LocalDateTime endDate);
    List<RefundDto> getRefundByBranchId(Long branchId);
    RefundDto getRefundById(Long id) throws Exception;
    void deleteRefund(Long id) throws Exception;

}
