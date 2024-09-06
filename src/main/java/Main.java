import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        // Create instances of repositories
        ProductRepo productRepo = new ProductRepo();
        OrderRepo orderRepo = new OrderMapRepo();

        // Create an instance of ShopService with constructor injection
        ShopService shopService = new ShopService(productRepo, orderRepo);

        // Create some sample products and add them to the productRepo
        Product product1 = new Product("prod1", "Product 1");
        Product product2 = new Product("prod2", "Product 2");
        Product product3 = new Product("prod3", "Product 3");

        // Add products to the productRepo
        productRepo.addProduct(product1);
        productRepo.addProduct(product2);
        productRepo.addProduct(product3);

        // Define three orders
        List<String> productIds1 = List.of("prod1", "prod2");
        List<String> productIds2 = List.of("prod2", "prod3");
        List<String> productIds3 = List.of("prod1", "prod3");

        // Add orders to ShopService
        try {
            Order order1 = shopService.addOrder(productIds1);
            System.out.println("Added Order 1: " + order1);

            Order order2 = shopService.addOrder(productIds2);
            System.out.println("Added Order 2: " + order2);

            Order order3 = shopService.addOrder(productIds3);
            System.out.println("Added Order 3: " + order3);
        } catch (ProductNotFoundException e) {
            System.err.println("Error adding order: " + e.getMessage());
        }

        System.out.println(productRepo);
        System.out.println(orderRepo);
        System.out.println(shopService);

    }
}