package com.dilip.posSystem.service.impl;

import com.dilip.posSystem.modal.Customer;
import com.dilip.posSystem.repository.CustomerRepository;
import com.dilip.posSystem.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) throws Exception {
        Customer existing = customerRepository.findById(id).orElseThrow(
                ()->new Exception("Customer not found")
        );
        existing.setFullName(customer.getFullName());
        existing.setPhone(customer.getPhone());
        existing.setEmail(customer.getEmail());
        existing.setUpdatedAt(LocalDateTime.now());
        return customerRepository.save(existing);
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {
        Customer existing = customerRepository.findById(id).orElseThrow(
                ()->new Exception("Customer not found")
        );
        customerRepository.delete(existing);
    }

    @Override
    public Customer getCustomer(Long id) throws Exception {
        return customerRepository.findById(id).orElseThrow(
                ()->new Exception("Customer not found")
        );
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomers(String keyword) {
        return customerRepository.search(keyword);
    }
}
