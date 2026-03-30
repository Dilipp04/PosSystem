package com.dilip.posSystem.mapper;

import com.dilip.posSystem.modal.Refund;
import com.dilip.posSystem.payload.dto.RefundDto;

import java.util.List;
import java.util.stream.Collectors;

public class RefundMapper {
    public static RefundDto toDTO(Refund refund){
        return RefundDto.builder()
                .id(refund.getId())
                .orderId(refund.getOrder().getId())
                .reason(refund.getReason())
                .amount(refund.getAmount())
//                .shiftReport(refund.getShiftReport())
                .shiftReportId(refund.getShiftReport()!= null?refund.getShiftReport().getId():null)
                .cashierName(refund.getCashier().getFullName())
                .branchId(refund.getBranch().getId()!=null?refund.getBranch().getId():null)
                .createdAt(refund.getCreatedAt())
                .build();
    }
    public static List<RefundDto> toDtoList(List<Refund> list){
        return list.stream().map(
                RefundMapper::toDTO
        ).collect(Collectors.toList());
    }
}
