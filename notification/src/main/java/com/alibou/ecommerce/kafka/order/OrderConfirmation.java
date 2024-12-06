package com.alibou.ecommerce.kafka.order;

import com.alibou.ecommerce.kafka.payment.PaymentMethod;
import jakarta.persistence.Embeddable;

import java.math.BigDecimal;
import java.util.List;
@Embeddable
public record OrderConfirmation(
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> products

) {
}
