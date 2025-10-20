package com.acm.point3.order;

import com.acm.point3.discounts.DiscountService;
import com.acm.point3.discounts.SeasonalCampaign;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Servicio que usa una estrategia de descuento.
 *
 * Reto 2 (Ambigüedad):
 *   - Con Basic y Premium activos, y SIN @Primary ni @Qualifier,
 *     Spring encuentra 2 DiscountService y falla:
 *     NoUniqueBeanDefinitionException (¡EVIDENCIA del Reto 2!).
 *
 * Reto 4 (@Qualifier):
 *   - Renombra Premium (p. ej. @Service("vipDiscountService")) o usa su id por defecto ("premiumDiscountService")
 *   - DESCOMENTA el constructor con @Qualifier para forzar ese bean.
 *
 * Reto 5 (Bean opcional):
 *   - @Autowired(required=false) en SeasonalCampaign.
 *   - Si existe ChristmasCampaign: aplica 5% extra. Si no: no pasa nada.
 */
@Service
public class OrderService {

    private final DiscountService discountService;

    @Autowired(required = false)
    private SeasonalCampaign seasonalCampaign;

    // === Constructor para Reto 2 y Reto 3: SIN @Qualifier (para provocar/resolver ambigüedad) ===
    public OrderService(DiscountService discountService) {
        this.discountService = discountService;
    }

    // === Constructor para Reto 4: usar @Qualifier para forzar Premium ===
    // DESCOMENTAR este constructor y COMENTAR el de arriba para el Reto 4.
    /*
    public OrderService(@Qualifier("vipDiscountService") DiscountService discountService) {
        // Alternativa si no renombras Premium:
        // public OrderService(@Qualifier("premiumDiscountService") DiscountService discountService) {
        this.discountService = discountService;
    }
    */

    public double checkout(double amount) {
        double base = discountService.apply(amount);
        if (seasonalCampaign != null) {
            base = seasonalCampaign.extraOff(base); // Reto 5: extra si existe campaña
        }
        return base;
    }

    public String discountInUse() {
        return discountService.name();
    }
}
