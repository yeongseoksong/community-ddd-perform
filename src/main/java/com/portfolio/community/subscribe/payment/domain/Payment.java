package com.portfolio.community.subscribe.payment.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@Getter

public class Payment {

    @Id
    private String orderId;

    private String paymentKey;

    private Long totalAmount;


    @Enumerated(EnumType.STRING)
    private PaymentState status;

    @Column(nullable = false)
    private LocalDateTime requestedAt;

    private LocalDateTime approvedAt;

    public Payment(String orderId, String paymentKey, Long totalAmount, PaymentState status, LocalDateTime requestedAt, LocalDateTime approvedAt) {
        this.orderId = orderId;
        this.paymentKey = paymentKey;
        this.totalAmount = totalAmount;
        this.status = status;
        this.requestedAt = requestedAt;
        this.approvedAt = approvedAt;
    }

}
