package com.dilip.posSystem.repository;

import com.dilip.posSystem.modal.Order;
import com.dilip.posSystem.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByStoreId(Long StoreId);
    List<Order> findByCashierId(Long cashierId);
    List<Order> findByStoreIdAndCreatedAtBetween(Long storeId, LocalDateTime from ,LocalDateTime to);
    List<Order> findByCashierAndCreatedAtBetween(User cashier,LocalDateTime from,LocalDateTime to);
    List<Order> findTop5ByStoreIdOrderByCreatedAtDesc(Long storeId);
}
