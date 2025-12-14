package id.wicaksana.demo.inventory.controller;

import id.wicaksana.demo.inventory.dto.InventoryReservationRequest;
import id.wicaksana.demo.inventory.dto.InventoryReservationResponse;
import id.wicaksana.demo.inventory.model.OrderItem;
import id.wicaksana.demo.inventory.model.ProductStock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);
    // simulates an in-memory database of stock.
    private final Map<Long, ProductStock> stockMap = new HashMap<>();

    public InventoryController() {
        // initializing mock inventory data.
        stockMap.put(101L, new ProductStock(101L, 10));
        stockMap.put(102L, new ProductStock(102L, 5));
        logger.info("Inventory initialized. P101: 10, P102: 5");
    }

    @PostMapping("/reserve")
    public InventoryReservationResponse reserveStock(@RequestBody InventoryReservationRequest request) {
        logger.info("Received stock reservation request.");

        // 1. Pre-check for stock availability
        for (OrderItem item : request.getItems()) {
            ProductStock stock = stockMap.get(item.getProductId());
            if (stock == null || stock.getQuantity() < item.getQuantity()) {
                String message = "Product " + item.getProductId() + " is out of stock (Requested: "
                        + item.getQuantity() + ", available: " + (stock != null ? stock.getQuantity(): 0) + ")";
                logger.warn(message);
                return new InventoryReservationResponse("OUT_OF_STOCK", message);
            }
        }

        // 2. if all check pass, decrement the stock (Reservation)
        for (OrderItem item : request.getItems()) {
            ProductStock stock = stockMap.get(item.getProductId());
            if (stock != null) {
                stock.setQuantity(stock.getQuantity() - item.getQuantity());
                logger.info("Reserved {} units of Product {}. New stock: {}",
                        item.getQuantity(), item.getProductId(), stock.getQuantity());
            }
        }
        return new InventoryReservationResponse("SUCCESS", "stock reserved successfully.");
    }

}
