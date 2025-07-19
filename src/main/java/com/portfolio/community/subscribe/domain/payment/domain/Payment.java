package com.portfolio.community.subscribe.domain.payment.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access =  AccessLevel.PROTECTED)
@Getter
public class Payment {

    @Id
    String paymentId;

    @Column(nullable = false, unique = true)
    String tossPaymentKey;

    String orderId;

    @Column(nullable = false)
    String tossOrderId;

    long totalAmount;


    @Column(nullable = false)
    LocalDateTime requestedAt;

    LocalDateTime approvedAt;

    // 기타 메서드 생략...
}
