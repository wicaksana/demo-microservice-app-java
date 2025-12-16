package id.wicaksana.inventory.controller;

import id.wicaksana.inventory.dto.InventoryReservationRequest;
import id.wicaksana.inventory.dto.InventoryReservationResponse;
import id.wicaksana.inventory.service.InventoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
    private static final Logger logger = LoggerFactory.getLogger(InventoryController.class);
    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{productId}")
    public ResponseEntity<InventoryReservationResponse> getInventoryById(@PathVariable @Positive Integer productId) {
        InventoryReservationResponse resp = inventoryService.getInventory(productId);
        logger.debug("Get result: {}", resp);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/reservation")
    public ResponseEntity<InventoryReservationResponse> reserveInventory(@RequestBody @Valid InventoryReservationRequest request) {
        InventoryReservationResponse resp = inventoryService.reserveInventory(request);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
