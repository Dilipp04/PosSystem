package com.dilip.posSystem.repository;

import com.dilip.posSystem.modal.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,Long> {
    Store findByAdminId(Long AdminId);
}
