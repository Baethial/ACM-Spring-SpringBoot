package dev.nelson.shoppingcart;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("singleton")
public class ShoppingCart {
    private final List<String> products = new ArrayList<>();

    public void addProduct(String product) {
        products.add(product);
    }

    public List<String> getProducts() {
        return products;
    }
}