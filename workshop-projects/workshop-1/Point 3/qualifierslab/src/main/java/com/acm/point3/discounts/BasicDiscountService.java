package com.acm.point3.discounts;

import org.springframework.stereotype.Service;
// import org.springframework.context.annotation.Primary;

/**
 * Reto 1: Implementación básica (10%).
 *
 * Reto 3: Para resolver la ambigüedad por defecto, DESCOMENTA @Primary.
 *   - Con @Primary activo y SIN @Qualifier en OrderService → se usará BASIC.
 *   - Resultado (sin campaña): 100 → 90.0
 *   - Con campaña (Reto 5, ChristmasCampaign activo): 90.0 → 85.5
 */
@Service
// @Primary  // ← DESCOMENTAR en Reto 3
public class BasicDiscountService implements DiscountService {
    @Override public double apply(double amount) { return amount * 0.90; }
    @Override public String name() { return "BASIC"; }
}
