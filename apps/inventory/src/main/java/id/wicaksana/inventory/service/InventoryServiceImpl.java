package id.wicaksana.inventory.service;

import id.wicaksana.inventory.dto.InventoryReservationRequest;
import id.wicaksana.inventory.dto.InventoryReservationResponse;
import id.wicaksana.inventory.model.InventoryItem;
import id.wicaksana.inventory.repository.InventoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {
    private static final Logger logger = LoggerFactory.getLogger(InventoryServiceImpl.class);
    private final InventoryRepository inventoryRepository;
    private final static InventoryReservationResponse NOT_FOUND =
            new InventoryReservationResponse("NOT_FOUND", "Product not found.");

    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public InventoryReservationResponse reserveInventory(InventoryReservationRequest request) {
        Optional<InventoryItem> found = inventoryRepository.findById(request.getProductId());
        InventoryReservationResponse response;
        if (found.isPresent()) {
            logger.debug("Product found: {}", found.get());

            if (found.get().getQuantity() > request.getQuantity()) {
                found.get().setQuantity(found.get().getQuantity() - request.getQuantity());
                response = new InventoryReservationResponse(
                        "SUCCESS",
                        "Enough stock for product ID: " + request.getProductId() + " qty: " + request.getQuantity());
            } else {
                response = new InventoryReservationResponse(
                        "OUT_OF_STOCK",
                        "Not enough stock for product ID: " + request.getProductId() + " qty: " + request.getQuantity());
            }
        } else {
            logger.debug("Product not found: {}", request);
            response = NOT_FOUND;
        }
        logger.debug("Reservation response: {}", response);
        return response;
    }

    @Override
    public InventoryReservationResponse getInventory(Integer productId) {
        return inventoryRepository.findById(productId)
                .map(inventory -> new InventoryReservationResponse(
                        "SUCCESS",
                        String.format("Inventory available for %s: %d", inventory.getName(), inventory.getQuantity())
                ))
                .orElse(NOT_FOUND);
    }
}
