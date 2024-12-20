package Iset.com.ecommerce.order;

import Iset.com.ecommerce.orderline.OrderLine;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "customer_order")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String reference;

  @Column(name = "total_amount", nullable = false)
  private BigDecimal totalAmount;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PaymentMethod paymentMethod;

  @Column(nullable = false)
  private Long customerId;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<OrderLine> orderLines;

  @Column(updatable = false, nullable = false)
  @org.springframework.data.annotation.CreatedDate
  private LocalDateTime createdDate;

  @Column(insertable = false)
  @org.springframework.data.annotation.LastModifiedDate
  private LocalDateTime lastModifiedDate;
}
