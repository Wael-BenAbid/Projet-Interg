package Iset.com.ecommerce.customer;

public record CustomerResponse(
        Long id,
        String firstname,
        String lastname,
        String email
) { }

