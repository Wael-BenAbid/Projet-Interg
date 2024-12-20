package Iset.com.ecommerce.order;

import Iset.com.ecommerce.product.PurchaseRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

@JsonInclude(Include.NON_EMPTY)
public record OrderRequest(
        Long id,
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should be specified")
        PaymentMethod paymentMethod,
        @NotNull(message = "Customer ID cannot be null")
        Long customerId,
        @NotEmpty(message = "You should purchase at least one product")
        List<PurchaseRequest> products
) {
}
