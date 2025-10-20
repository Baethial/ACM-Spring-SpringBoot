package dev.jaqs.infiniteloop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final OrderService orderService;

    @Autowired
    public InventoryService(OrderService orderService) {
        this.orderService = orderService;
        System.out.println("InventoryService initializing...");
    }

    public void checkStock() {
        System.out.println( "Checking stock...");
    }
}
