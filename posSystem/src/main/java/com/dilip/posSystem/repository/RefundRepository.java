package com.dilip.posSystem.repository;

import com.dilip.posSystem.modal.Order;
import com.dilip.posSystem.modal.Refund;
import com.dilip.posSystem.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundRepository extends JpaRepository<Refund,Long> {
    List<Refund> findByCashierId(Long cashierId);
    List<Refund> findByShiftReportId(Long shiftReportId);
    List<Refund> findByCashierIdAndCreatedAtBetween(
            Long cashierId,
            LocalDateTime from ,
            LocalDateTime to
    );
    List<Refund> findByBranchId(Long branchId);

}
