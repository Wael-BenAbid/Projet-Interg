    package Iset.com.ecommerce.order;

    import com.fasterxml.jackson.annotation.JsonInclude;
    import com.fasterxml.jackson.annotation.JsonInclude.Include;
    import jakarta.validation.constraints.NotNull;

    import java.math.BigDecimal;

    @JsonInclude(Include.NON_EMPTY)
    public record OrderResponse(
        Long id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        @NotNull
        Long customerId
    ) {

    }
