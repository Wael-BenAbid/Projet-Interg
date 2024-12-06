package Iset.com.ecommerce.kafka;

import Iset.com.ecommerce.customer.CustomerResponse;
import Iset.com.ecommerce.order.PaymentMethod;
import Iset.com.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
