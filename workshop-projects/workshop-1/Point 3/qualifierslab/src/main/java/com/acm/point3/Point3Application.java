package com.acm.point3;

import com.acm.point3.order.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Punto 3 — La Conspiración de los Qualifiers
 *
 * Este main imprime:
 *  - La estrategia de descuento en uso (BASIC o PREMIUM)
 *  - El total para un monto de 100
 *  - (Runner extra) Cuántos beans DiscountService detecta Spring (para Reto 2)
 *
 * GUÍA RÁPIDA (según los Retos del enunciado):
 *  Reto 1: Crea Basic y Premium (ambos con @Service).
 *  Reto 2: Inyéctalas en OrderService SIN @Qualifier → verás error de ambigüedad.
 *  Reto 3: Agrega @Primary a una (p.ej., Basic) → arranca y usa esa por defecto.
 *  Reto 4: Renombra Premium y usa @Qualifier(<nombre>) en OrderService → fuerza Premium.
 *  Reto 5: Usa @Autowired(required=false) con un bean opcional (ChristmasCampaign).
 */
@SpringBootApplication
public class Point3Application {

    public static void main(String[] args) {
        SpringApplication.run(Point3Application.class, args);
    }

    /** Imprime estrategia y total para 100 (se usa en todos los Retos). */
    @Bean
    CommandLineRunner demo(OrderService orderService) {
        return args -> {
            System.out.println("== DEMO PUNTO 3 ==");
            System.out.println("Estrategia en uso: " + orderService.discountInUse());
            System.out.println("Total para 100: " + orderService.checkout(100));
        };
    }

    /** Runner auxiliar para Reto 2: auditar cuántos DiscountService hay. */
    @Bean
    CommandLineRunner whoIsThere(java.util.List<com.acm.point3.discounts.DiscountService> discounts) {
        return args -> {
            System.out.println("== BEANS DiscountService encontrados ==");
            discounts.forEach(d -> System.out.println(" - " + d.getClass().getName()));
            System.out.println("Total: " + discounts.size());
        };
    }
}
