import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductMapRepo extends ProductRepo {
    private Map<String, Product> products = new HashMap<>();


    @Override
    public Optional<Product> getProductById(String id) {
        // Use Optional.ofNullable to handle cases where the product might not exist
        return Optional.ofNullable(products.get(id));
    }


    public Product addProduct(Product product) {
        products.put(product.id(), product);
        return product;
    }
}