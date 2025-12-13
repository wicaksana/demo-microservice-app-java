package id.wicaksana.demo.order.controller;

import id.wicaksana.demo.order.dto.InventoryReservationRequest;
import id.wicaksana.demo.order.dto.InventoryReservationResponse;
import id.wicaksana.demo.order.model.Order;
import id.wicaksana.demo.order.model.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final RestTemplate restTemplate;
    private final String inventoryServiceUrl = "http://localhost:8081";

    public OrderController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody List<OrderItem> items) {
        long orderId = System.currentTimeMillis();
        Order newOrder = new Order(orderId, items, "PENDING");

        logger.info("Order {} created with PENDING status.", orderId);

        InventoryReservationRequest request = new InventoryReservationRequest(items);

        try {
            InventoryReservationResponse response = restTemplate.postForObject(
                    inventoryServiceUrl,
                    request,
                    InventoryReservationResponse.class
            );

            if (response != null && "SUCCESS".equals(response.getStatus())) {
                newOrder.setStatus("PROCESSING");
                logger.info("Inventory SUCCESS. Order {} status updated to PROCESSING", orderId);
                return ResponseEntity.ok(newOrder);
            } else {
                newOrder.setStatus("FAILED: Order " + (response != null ? response.getMessage() : "Unknown failure"));
                logger.error("Inventory FAILED. Order {} status updated to FAILED", orderId);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(newOrder);
            }
        } catch (Exception e) {
            newOrder.setStatus("FAILED: INVENTORY_UNAVAILABLE");
            logger.error("Communication with Inventory Service failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(newOrder);
        }
    }
}
