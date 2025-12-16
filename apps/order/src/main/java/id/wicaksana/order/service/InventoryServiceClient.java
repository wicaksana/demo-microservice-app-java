package id.wicaksana.order.service;

import id.wicaksana.order.dto.InventoryReservationRequest;
import id.wicaksana.order.dto.InventoryReservationResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(url = "/api/v1/inventory")
public interface InventoryServiceClient {

    @GetExchange("/{productId}")
    InventoryReservationResponse getInventory(@PathVariable Integer productId);

    @PostExchange("/reservation")
    InventoryReservationResponse reserveInventory(@RequestBody InventoryReservationRequest request);
}
