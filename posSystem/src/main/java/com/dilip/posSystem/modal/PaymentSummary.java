package com.dilip.posSystem.modal;

import com.dilip.posSystem.domain.PaymentType;
import lombok.Data;

@Data
public class PaymentSummary {
    private PaymentType type;
    private Double totalAmount;
    private int transactionCount;
    private double percentage;
}
