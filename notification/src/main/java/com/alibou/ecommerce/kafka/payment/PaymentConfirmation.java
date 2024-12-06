package com.alibou.ecommerce.kafka.payment;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
@Embeddable
public record PaymentConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstname,
        String customerLastname,
        String customerEmail
) {
}
