package id.wicaksana.inventory.service;

import id.wicaksana.inventory.dto.InventoryReservationRequest;
import id.wicaksana.inventory.dto.InventoryReservationResponse;

import java.util.Optional;

public interface InventoryService {
    InventoryReservationResponse reserveInventory(InventoryReservationRequest request);
    InventoryReservationResponse getInventory(Integer productId);
}
