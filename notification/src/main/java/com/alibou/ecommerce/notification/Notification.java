package com.alibou.ecommerce.notification;

import com.alibou.ecommerce.kafka.order.OrderConfirmation;
import com.alibou.ecommerce.kafka.payment.PaymentConfirmation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;


import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private NotificationType type;
    private LocalDateTime notificationDate;

    @Embedded
    private OrderConfirmation orderConfirmation;

    @Embedded
    private PaymentConfirmation paymentConfirmation;
}
