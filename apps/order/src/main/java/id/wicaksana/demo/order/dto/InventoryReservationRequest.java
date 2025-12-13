package id.wicaksana.demo.order.dto;

import id.wicaksana.demo.order.model.OrderItem;

import java.util.List;

public class InventoryReservationRequest {
    private List<OrderItem> items;

    public InventoryReservationRequest() {
    }

    public InventoryReservationRequest(List<OrderItem> items) {
        this.items = items;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
