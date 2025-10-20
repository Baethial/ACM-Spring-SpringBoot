package dev.jaqs.infiniteloop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private InventoryService inventoryService;

    @Autowired
    public OrderService(@Lazy InventoryService inventoryService) {
        this.inventoryService = inventoryService;
        System.out.println("OrderService initializing...");
    }

//    @Autowired
//    public void setInventoryService(InventoryService inventoryService) {
//        this.inventoryService = inventoryService;
//        System.out.println("OrderService initializing using setter injection...");
//    }

    public void processOrder() {
        System.out.println("Processing order...");
    }
}
