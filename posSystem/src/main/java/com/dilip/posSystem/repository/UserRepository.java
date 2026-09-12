package com.dilip.posSystem.repository;

import com.dilip.posSystem.modal.User;
import com.dilip.posSystem.modal.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    List<User> findByStore(Store store);

    List<User> findByBranchId(Long branchId);
}
