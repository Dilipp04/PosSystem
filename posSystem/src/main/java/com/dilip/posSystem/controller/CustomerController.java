package com.dilip.posSystem.controller;

import com.dilip.posSystem.modal.Customer;
import com.dilip.posSystem.payload.response.ApiResponse;
import com.dilip.posSystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;


    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(
            @RequestBody Customer customer
    ){
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @RequestBody Customer customer,
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(customerService.updateCustomer(id,customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(
            @PathVariable Long id
    ) throws Exception {
        customerService.deleteCustomer(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Customer deleted successfully!");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(
            @PathVariable Long id
    ) throws Exception {
        return ResponseEntity.ok(customerService.getCustomer(id));
    }

    @GetMapping("")
    public ResponseEntity<List<Customer>> getAllCustomers() throws Exception {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> getAllCustomers(
            @RequestParam("keyword") String keyword
    ) throws Exception {
        return ResponseEntity.ok(customerService.searchCustomers(keyword));
    }





}
