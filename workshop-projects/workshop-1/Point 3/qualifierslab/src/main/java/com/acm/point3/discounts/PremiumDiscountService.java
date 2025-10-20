package com.acm.point3.discounts;

import org.springframework.stereotype.Service;

/**
 * Reto 1: Implementación premium (20%).
 *
 * Reto 4: Para forzar su uso con @Qualifier:
 *   Opción A: Dejar @Service por defecto y en OrderService usar @Qualifier("premiumDiscountService")
 *   Opción B (recomendada por el reto): RENOMBRAR el bean:
 *       @Service("vipDiscountService")
 *     y en OrderService usar @Qualifier("vipDiscountService")
 *
 * Resultado esperado:
 *   - Sin campaña: 100 → 80.0
 *   - Con campaña (Reto 5): 80.0 → 76.0
 */
@Service
// @Service("vipDiscountService")  // ← OPCIÓN B para Reto 4
public class PremiumDiscountService implements DiscountService {
    @Override public double apply(double amount) { return amount * 0.80; }
    @Override public String name() { return "PREMIUM"; }
}
