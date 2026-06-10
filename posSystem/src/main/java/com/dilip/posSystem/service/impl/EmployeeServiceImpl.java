package com.dilip.posSystem.service.impl;

import com.dilip.posSystem.domain.UserRole;
import com.dilip.posSystem.mapper.UserMapper;
import com.dilip.posSystem.modal.Store;
import com.dilip.posSystem.modal.User;
import com.dilip.posSystem.payload.dto.UserDto;
import com.dilip.posSystem.repository.StoreRepository;
import com.dilip.posSystem.repository.UserRepository;
import com.dilip.posSystem.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new Exception("Store not found"));

        User user = UserMapper.toEntity(employee);
        user.setStore(store);
        user.setPassword(passwordEncoder.encode(employee.getPassword()));

        User savedEmployee = userRepository.save(user);

        return UserMapper.toDTO(savedEmployee);
    }


    @Override
    public UserDto updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception {
        User existingEmployee = userRepository.findById(employeeId).orElseThrow(
                () -> new Exception("Employee Not Exist with given id"));

        Store store = storeRepository.findById(employeeDetails.getStoreId()).orElseThrow(
                () -> new Exception("Store not found"));

        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setFullName(employeeDetails.getFullName());
        existingEmployee.setPassword(passwordEncoder.encode(employeeDetails.getPassword()));
        existingEmployee.setRole(employeeDetails.getRole());
        existingEmployee.setStore(store);

        return UserMapper.toDTO(userRepository.save(existingEmployee));
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {
        User employee = userRepository.findById(employeeId).orElseThrow(
                () -> new Exception("Employee Not Exist with given id"));
        userRepository.delete(employee);
    }

    @Override
    public List<UserDto> findStoreEmployees(Long storeId, UserRole role) throws Exception {
        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new Exception("Store not found"));
        List<User> employees = userRepository.findByStore(store);
        return employees.stream().map(UserMapper::toDTO).collect(Collectors.toList());
    }


}
