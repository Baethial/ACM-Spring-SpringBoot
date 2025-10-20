package com.acm.point3.discounts;

import org.springframework.stereotype.Component;

/**
 * Reto 5: Implementación opcional (5% adicional al final).
 *
 * - Con @Component ACTIVO → se aplica el 5% extra.
 *   Ej: BASIC 100 → 90 → 85.5 ;  PREMIUM 100 → 80 → 76
 *
 * - Si COMENTAS @Component → el bean NO existe y NO se aplica el extra.
 */
@Component   //  Comentarlo / activarlo para demostrar el comportamiento opcional
public class ChristmasCampaign implements SeasonalCampaign {
    @Override public double extraOff(double amount) { return amount * 0.95; }
}
