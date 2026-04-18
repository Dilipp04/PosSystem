package com.dilip.posSystem.controller;

import com.dilip.posSystem.payload.dto.ShiftReportDto;
import com.dilip.posSystem.service.ShiftReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shift-reports")
public class ShiftReportController {
    private final ShiftReportService shiftReportService;

    @PostMapping("/start")
    public ResponseEntity<ShiftReportDto> startShift() throws Exception {
        return ResponseEntity.ok(
                shiftReportService.startShift()
        );
    }

    @PatchMapping("/end")
    public ResponseEntity<ShiftReportDto> endShift(Long shiftReportId) throws Exception {
        return ResponseEntity.ok(
                shiftReportService.endShift(null,null)
        );
    }

    @GetMapping("/current")
    public ResponseEntity<ShiftReportDto> getCurrentShiftProgress() throws Exception {
        return ResponseEntity.ok(
                shiftReportService.getCurrentShiftProgress(null)
        );
    }

    @GetMapping("/cashier/{cashierId}/by-date")
    public ResponseEntity<ShiftReportDto>  getShiftReportByDate(
            @PathVariable Long cashierId,
            @RequestParam @DateTimeFormat (iso=DateTimeFormat.ISO.DATE) LocalDateTime date
    ) throws Exception{
        return ResponseEntity.ok(
                shiftReportService.getShiftByCashierAndDate(cashierId,date)
        );
    }

    @GetMapping("/cashier/{cashierId}")
    public ResponseEntity<List<ShiftReportDto>> getShiftReportByCashier(
            @PathVariable Long cashierId
    ){
        return ResponseEntity.ok(
            shiftReportService.getShiftReportByCashierId(cashierId)
        );
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<ShiftReportDto>> getShiftReportByBranch(
            @PathVariable Long branchId
    ){
        return ResponseEntity.ok(
                shiftReportService.getShiftReportByBranchId(branchId)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShiftReportDto> getById(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(shiftReportService.getShiftReportById(id));
    }









    

}
