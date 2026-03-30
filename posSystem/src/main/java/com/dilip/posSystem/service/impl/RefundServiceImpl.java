package com.dilip.posSystem.service.impl;

import com.dilip.posSystem.exceptions.UserException;
import com.dilip.posSystem.mapper.RefundMapper;
import com.dilip.posSystem.modal.Branch;
import com.dilip.posSystem.modal.Order;
import com.dilip.posSystem.modal.Refund;
import com.dilip.posSystem.modal.User;
import com.dilip.posSystem.payload.dto.RefundDto;
import com.dilip.posSystem.repository.BranchRepository;
import com.dilip.posSystem.repository.OrderRepository;
import com.dilip.posSystem.repository.RefundRepository;
import com.dilip.posSystem.service.RefundService;
import com.dilip.posSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {
    private final UserService userService;
    private final OrderRepository orderRepository;
    private final RefundRepository refundRepository;
    @Override
    public RefundDto createRefund(RefundDto refundDto) throws Exception {
        User cashier = userService.getCurrentUser();
        Order order = orderRepository.findById(refundDto.getOrderId()).orElseThrow(
                ()-> new Exception("Order not found")
        );
        Branch branch = order.getBranch();

        Refund createdRefund = Refund.builder()
                .order(order)
                .reason(refundDto.getReason())
                .amount(refundDto.getAmount())
                .cashier(cashier)
                .branch(branch)
                .build();
        return RefundMapper.toDTO(refundRepository.save(createdRefund));
    }

    @Override
    public List<RefundDto> getAllRefunds() {
        return RefundMapper.toDtoList(refundRepository.findAll());
    }

    @Override
    public List<RefundDto> getRefundByCashier(Long cashierId) {
        return RefundMapper.toDtoList(refundRepository.findByCashierId(cashierId));
    }

    @Override
    public List<RefundDto> getRefundByShiftReport(Long shiftReportId) {
        return RefundMapper.toDtoList(refundRepository.findByShiftReportId(shiftReportId));
    }

    @Override
    public List<RefundDto> getRefundByCashierAndDateRange(Long cashierId, LocalDateTime startDate, LocalDateTime endDate) {
        return RefundMapper.toDtoList(refundRepository.findByCashierIdAndCreatedAtBetween(cashierId,startDate,endDate));
    }

    @Override
    public List<RefundDto> getRefundByBranchId(Long branchId) {
        return RefundMapper.toDtoList(refundRepository.findByBranchId(branchId));
    }

    @Override
    public RefundDto getRefundById(Long id) throws Exception {
        return RefundMapper.toDTO(refundRepository.findById(id).orElseThrow(
                ()->new Exception("Refund Not found")
        ));
    }

    @Override
    public void deleteRefund(Long id) throws Exception {
        refundRepository.deleteById(id);
    }
}
