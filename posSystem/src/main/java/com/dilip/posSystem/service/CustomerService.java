package com.dilip.posSystem.service;

import com.dilip.posSystem.modal.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id,Customer customer) throws Exception;
    void deleteCustomer(Long id) throws Exception;
    Customer getCustomer(Long id) throws Exception;
    List<Customer> getAllCustomers();
    List<Customer> searchCustomers(String keyword);



}
