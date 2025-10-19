package dev.nelson.shoppingcart;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ShoppingCartApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ShoppingCartApplication.class, args);
        ProductService productService = context.getBean(ProductService.class);


        ShoppingCart user1Cart = productService.createShoppingCart();
        user1Cart.addProduct("Laptop");
        user1Cart.addProduct("Mouse");

        ShoppingCart user2Cart = productService.createShoppingCart();
        user2Cart.addProduct("Teléfono");
        user2Cart.addProduct("Audífonos");

        ShoppingCart user3Cart = productService.createShoppingCart();
        user3Cart.addProduct("Tablet");

        System.out.println("\n--- Contenido de los carritos ---");
        System.out.println("Usuario 1 -> " + user1Cart.getProducts());
        System.out.println("Usuario 2 -> " + user2Cart.getProducts());
        System.out.println("Usuario 3 -> " + user3Cart.getProducts());

        System.out.println("\n--- Instancias en memoria ---");
        System.out.println("Carrito Usuario 1: " + user1Cart);
        System.out.println("Carrito Usuario 2: " + user2Cart);
        System.out.println("Carrito Usuario 3: " + user3Cart);
    }
}