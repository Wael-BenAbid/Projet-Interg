package Iset.com.ecommerce.order;

import java.util.List;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<Long> createOrder(
          @RequestBody @Valid OrderRequest orderRequest
  ) {
    Long orderId = orderService.createOrder(orderRequest);
    return ResponseEntity.ok(orderId);
  }

  @GetMapping
  public ResponseEntity<List<OrderResponse>> findAllOrders() {
    List<OrderResponse> orders = orderService.findAllOrders();
    return ResponseEntity.ok(orders);
  }

  @GetMapping("/{orderId}")
  public ResponseEntity<OrderResponse> findOrderById(
          @PathVariable Long orderId
  ) {
    OrderResponse orderResponse = orderService.findById(orderId);
    return ResponseEntity.ok(orderResponse);
  }
}
