package com.dilip.posSystem.controller;

import com.dilip.posSystem.domain.UserRole;
import com.dilip.posSystem.payload.dto.UserDto;
import com.dilip.posSystem.payload.response.ApiResponse;
import com.dilip.posSystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/store/{storeId}")
    public ResponseEntity<UserDto> createStoreEmployee(
            @PathVariable Long storeId,
            @RequestBody UserDto userDto) throws Exception {
        UserDto employee = employeeService.createStoreEmployee(userDto, storeId);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/branch/{branchId}")
    public ResponseEntity<UserDto> createBranchEmployee(
            @PathVariable Long branchId,
            @RequestBody UserDto userDto) throws Exception {
        UserDto employee = employeeService.createBranchEmployee(userDto, branchId);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateEmployee(
            @PathVariable Long id,
            @RequestBody UserDto userDto) throws Exception {
        UserDto employee = employeeService.updateEmployee(id, userDto);
        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(
            @PathVariable Long id) throws Exception {
        employeeService.deleteEmployee(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Employee deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity<List<UserDto>> storeEmployees(
            @PathVariable Long id,
            @RequestParam(required = false) UserRole userRole) throws Exception {
        List<UserDto> employees = employeeService.findStoreEmployees(id, userRole);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<List<UserDto>> branchEmployees(
            @PathVariable Long id,
            @RequestParam(required = false) UserRole userRole) throws Exception {
        List<UserDto> employees = employeeService.findBranchEmployees(id, userRole);
        return ResponseEntity.ok(employees);
    }

}
