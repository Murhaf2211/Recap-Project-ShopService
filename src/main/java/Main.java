import java.util.List;

public class Main {
    public static void main(String[] args) {
        ShopService shopService = new ShopService();
        List<String> productIds = List.of("prod1", "prod2");

        try {
            Order order = shopService.addOrder(productIds);
            System.out.println("Order successfully created: " + order);
        } catch (ProductNotFoundException e) {
            System.out.println("Failed to create order: " + e.getMessage());
        }
    }
}
