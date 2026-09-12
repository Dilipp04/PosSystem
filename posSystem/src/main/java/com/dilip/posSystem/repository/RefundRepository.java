package com.dilip.posSystem.repository;

import com.dilip.posSystem.modal.Refund;
import com.dilip.posSystem.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface RefundRepository extends JpaRepository<Refund, Long> {

    List<Refund> findByCashierIdAndCreatedAtBetween(
            Long cashier,
            LocalDateTime from,
            LocalDateTime to);

    List<Refund> findByCashierId(Long id);

    List<Refund> findByShiftReportId(Long id);

    List<Refund> findByBranchId(Long id);

}
