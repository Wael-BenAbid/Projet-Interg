package Iset.com.ecommerce.customer;

import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

  public Customer toCustomer(CustomerRequest request) {
    return Customer.builder()
            .id(request.id())
            .firstname(request.firstname())
            .lastname(request.lastname())
            .email(request.email())
            .address(request.address())
            .build();
  }

  public CustomerResponse fromCustomer(Customer customer) {
    return new CustomerResponse(
            customer.getId(),  // L'ID reste un Long
            customer.getFirstname(),
            customer.getLastname(),
            customer.getEmail(),
            customer.getAddress()
    );
  }
}
