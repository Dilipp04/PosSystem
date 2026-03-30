package com.zosh.model;

import com.dilip.posSystem.modal.*;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShiftReport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDateTime shiftStart;
    private LocalDateTime shiftEnd;

    private Double totalSales = 0.0;
    private Double totalRefunds = 0.0;
    private Double netSales = 0.0;
    private int totalOrders = 0;

    @ManyToOne
    private User cashier;

    @ManyToOne
    private Branch branch;

    private List<PaymentSummary> paymentSummaries;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "shift_report_top_products",
            joinColumns = @JoinColumn(name = "shift_report_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> topSellingProducts;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "shift_report_recent_orders",
            joinColumns = @JoinColumn(name = "shift_report_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<Order> recentOrders;

    @OneToMany(mappedBy = "shiftReport", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Refund> refunds;
}