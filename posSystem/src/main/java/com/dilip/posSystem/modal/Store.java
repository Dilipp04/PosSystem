package com.dilip.posSystem.modal;

import com.dilip.posSystem.domain.StoreStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @OneToOne
    private User admin;

    @ElementCollection
    private List<String> workingDays;

    private LocalTime openTime;

    private LocalTime closeTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String description;

    private String storeType;
    private StoreStatus status;

    @Embedded
    private StoreContact contact = new StoreContact();

    @PrePersist
    protected void onCreated(){
        createdAt = LocalDateTime.now();
        status = StoreStatus.PENDING;
    }

    @PreUpdate
    protected void onUpdated(){
        updatedAt = LocalDateTime.now();
    }


}
