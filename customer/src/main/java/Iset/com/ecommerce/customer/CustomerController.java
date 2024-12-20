package Iset.com.ecommerce.customer;

import java.util.List;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService service;

  @PostMapping
  public ResponseEntity<Long> createCustomer(@RequestBody @Valid CustomerRequest request) {
    return ResponseEntity.ok(this.service.createCustomer(request));
  }

  @PutMapping
  public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {
    this.service.updateCustomer(request);
    return ResponseEntity.accepted().build();
  }

  @GetMapping
  public ResponseEntity<List<CustomerResponse>> findAll() {
    return ResponseEntity.ok(this.service.findAllCustomers());
  }

  @GetMapping("/{customer-id}")
  public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") Long customerId) {
    return ResponseEntity.ok(this.service.findById(customerId));
  }

  @DeleteMapping("/{customer-id}")
  public ResponseEntity<Void> delete(@PathVariable("customer-id") Long customerId) {
    this.service.deleteCustomer(customerId);
    return ResponseEntity.noContent().build();
  }
}
