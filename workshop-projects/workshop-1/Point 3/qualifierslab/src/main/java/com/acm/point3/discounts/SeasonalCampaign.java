package com.acm.point3.discounts;

/**
 * Reto 5: Bean OPCIONAL para un descuento extra estacional.
 * Se inyecta con @Autowired(required=false) en OrderService.
 */
public interface SeasonalCampaign {
    double extraOff(double amount);
}
