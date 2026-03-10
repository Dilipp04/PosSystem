package com.dilip.posSystem.repository;

import com.dilip.posSystem.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String username);
}
