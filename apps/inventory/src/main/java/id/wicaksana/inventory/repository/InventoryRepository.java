package id.wicaksana.inventory.repository;

import id.wicaksana.inventory.model.InventoryItem;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository {
    Optional<InventoryItem> findById(Integer productId);
}
