    package Iset.com.ecommerce.order;

    import Iset.com.ecommerce.customer.CustomerClient;
    import Iset.com.ecommerce.exception.BusinessException;
    import Iset.com.ecommerce.orderline.OrderLineRequest;
    import Iset.com.ecommerce.orderline.OrderLineService;
    import Iset.com.ecommerce.payment.PaymentClient;
    import Iset.com.ecommerce.payment.PaymentRequest;
    import Iset.com.ecommerce.product.ProductClient;
    import Iset.com.ecommerce.product.PurchaseRequest;
    import Iset.com.ecommerce.kafka.OrderConfirmation;
    import Iset.com.ecommerce.kafka.OrderProducer;
    import jakarta.persistence.EntityNotFoundException;
    import lombok.RequiredArgsConstructor;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.util.List;
    import java.util.stream.Collectors;

    @Service
    @RequiredArgsConstructor
    public class OrderService {

        private final OrderRepository repository;
        private final OrderMapper mapper;
        private final CustomerClient customerClient;
        private final PaymentClient paymentClient;
        private final ProductClient productClient;
        private final OrderLineService orderLineService;
        private final OrderProducer orderProducer;

        @Transactional
        public Long createOrder(OrderRequest request) {
            // Récupérer les informations du client via le CustomerClient
            var customer = this.customerClient.findCustomerById(request.customerId())
                    .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

            // Acheter les produits via le ProductClient
            var purchasedProducts = productClient.purchaseProducts(request.products());

            // Sauvegarder la commande dans la base de données
            var order = this.repository.save(mapper.toOrder(request));

            // Sauvegarder les lignes de commande pour chaque produit acheté
            for (PurchaseRequest purchaseRequest : request.products()) {
                orderLineService.saveOrderLine(
                        new OrderLineRequest(
                                null,
                                order.getId(),
                                purchaseRequest.productId(),
                                purchaseRequest.quantity()
                        )
                );
            }

            // Créer une requête de paiement
            var paymentRequest = new PaymentRequest(
                    request.amount(),
                    request.paymentMethod(),
                    order.getId(),
                    order.getReference(),
                    customer
            );
            paymentClient.requestOrderPayment(paymentRequest);  // Appel du service de paiement

            // Envoyer une confirmation de commande via Kafka
            orderProducer.sendOrderConfirmation(
                    new OrderConfirmation(
                            request.reference(),
                            request.amount(),
                            request.paymentMethod(),
                            customer,
                            purchasedProducts
                    )
            );

            return order.getId();  // Retourner l'ID de la commande
        }

        public List<OrderResponse> findAllOrders() {
            // Récupérer toutes les commandes et les convertir en réponses
            return this.repository.findAll()
                    .stream()
                    .map(this.mapper::fromOrder)
                    .collect(Collectors.toList());
        }

        public OrderResponse findById(Long id) {
            // Récupérer une commande par son ID et la convertir en réponse
            return this.repository.findById(id)
                    .map(this.mapper::fromOrder)
                    .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
        }
    }
