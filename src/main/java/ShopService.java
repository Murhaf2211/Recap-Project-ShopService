import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();

    public Order addOrder(List<String> productIds) {
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isEmpty()) {
                throw new ProductNotFoundException("Product with ID: " + productId + " could not be ordered because it doesn't exist.");
            }
            products.add(productToOrder.get());
        }
        Instant timestamp = Instant.now();
        Order newOrder = new Order(UUID.randomUUID().toString(), products, OrderStatus.NEW, timestamp);

        return orderRepo.addOrder(newOrder);
    }
    public void updateOrder(String orderId, OrderStatus newStatus) {

        Optional<Order> existingOrder = orderRepo.getOrderById(orderId);

        if (existingOrder.isPresent()) {
            Order updatedOrder = existingOrder.get().withStatus(newStatus);
            orderRepo.updateOrder(updatedOrder);
        } else {
            throw new IllegalArgumentException("Order with ID: " + orderId + " not found.");
        }
    }

    // New method to return orders by status
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepo.getOrders()   // Assuming getAllOrders() exists in OrderRepo
                .stream()
                .filter(order -> order.status() == status)
                .collect(Collectors.toList());
    }

    public void updateOrderStatus(String orderId, OrderStatus newStatus) {
        Optional<Order> existingOrder = orderRepo.getOrderById(orderId);

        if (existingOrder.isPresent()) {
            Order updatedOrder = new Order(
                    existingOrder.get().id(),
                    existingOrder.get().products(),
                    newStatus,
                    existingOrder.get().timestamp()
            );
            orderRepo.updateOrder(updatedOrder);
        } else {
            System.out.println("Order with ID: " + orderId + " not found!");
        }
    }
}
