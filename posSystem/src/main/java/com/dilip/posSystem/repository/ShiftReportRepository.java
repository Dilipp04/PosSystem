package com.dilip.posSystem.repository;

import com.dilip.posSystem.modal.ShiftReport;
import com.dilip.posSystem.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ShiftReportRepository extends JpaRepository<ShiftReport,Long> {
    List<ShiftReport> findByCashierId(Long id);
    List<ShiftReport> findByBranchId(Long id);
    Optional<ShiftReport> findTopByCashierAndShiftEndIsNullOrderByShiftStartDesc(User cashier);
    Optional<ShiftReport> findByCashierAndShiftStartBetween(User cashier, LocalDateTime start,LocalDateTime end);
}
