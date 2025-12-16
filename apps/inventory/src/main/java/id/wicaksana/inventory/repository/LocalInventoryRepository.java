package id.wicaksana.inventory.repository;

import id.wicaksana.inventory.model.InventoryItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class LocalInventoryRepository implements InventoryRepository {
    private static final Logger logger = LoggerFactory.getLogger(LocalInventoryRepository.class);
    private final ConcurrentHashMap<Integer, InventoryItem> storage = new ConcurrentHashMap<>();

    public LocalInventoryRepository() {
        logger.debug("Initiate the local database..");
        storage.put(1, new InventoryItem(1, "Book A", 10));
        storage.put(2, new InventoryItem(2, "Laptop X", 5));
    }

    @Override
    public Optional<InventoryItem> findById(Integer productId) {
        return Optional.ofNullable(storage.get(productId));
    }
}
