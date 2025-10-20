package com.acm.point3.discounts;

/** Reto 1: Interface base de descuentos. */
public interface DiscountService {
    double apply(double amount);
    String name();
}
