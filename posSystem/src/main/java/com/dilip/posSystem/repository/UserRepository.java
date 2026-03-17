package com.dilip.posSystem.repository;

import com.dilip.posSystem.modal.Store;
import com.dilip.posSystem.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String username);

    List<User> findByStore(Store store);
    List<User> findByBranchId(Long branchId);
}
