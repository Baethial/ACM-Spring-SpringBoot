package dev.nelson.shoppingcart;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ApplicationContext context;

    public ShoppingCart createShoppingCart() {
        return context.getBean(ShoppingCart.class);
    }
}

