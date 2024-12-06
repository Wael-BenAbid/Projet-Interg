package Iset.com.ecommerce.payment;

import Iset.com.ecommerce.customer.CustomerResponse;
import Iset.com.ecommerce.order.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
    BigDecimal amount,
    PaymentMethod paymentMethod,
    Long orderId,
    String orderReference,
    CustomerResponse customer
) {
}
