import java.time.Instant;
import java.util.List;

public record Order(
        String id,
        List<Product> products,
        OrderStatus status,
        Instant timestamp

) {
    public Order(String id, List<Product> products) {
        this(id, products, OrderStatus.PROCESSING, Instant.now());
    }
}
