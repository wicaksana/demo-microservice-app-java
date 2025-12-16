package id.wicaksana.order.service;

import id.wicaksana.order.dto.InventoryReservationRequest;
import id.wicaksana.order.dto.InventoryReservationResponse;
import id.wicaksana.order.dto.OrderRequest;
import id.wicaksana.order.dto.OrderResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    private final InventoryServiceClient inventoryClient;

    @Autowired
    public OrderServiceImpl(InventoryServiceClient inventoryClient) {
        this.inventoryClient = inventoryClient;
    }

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        long orderId = System.currentTimeMillis();
        String status = "";

        InventoryReservationRequest inventoryReq =
                new InventoryReservationRequest(request.productId(), request.quantity());

        InventoryReservationResponse inventoryResp = inventoryClient.reserveInventory(inventoryReq);
        logger.debug("Inventory response: {}", inventoryResp);

        if (inventoryResp.status().equals("SUCCESS"))
            status = "Successfully ordered";
        else if (inventoryResp.status().equals("NOT_FOUND")) {
            status = "Product not found.";
        } else if (inventoryResp.status().equals("OUT_OF_STOCK")) {
            status = "Not enough inventory to serve the order";
        }

        OrderResponse response = new OrderResponse(orderId, request.productId(), request.quantity(), status);
        logger.debug("Create new order: {}", response);

        return response;
    }
}
