package id.wicaksana.inventory.dto;

import jakarta.validation.constraints.Positive;

public class InventoryReservationRequest {
    @Positive
    private Integer productId;
    @Positive
    private Integer quantity;

    public InventoryReservationRequest() {
    }

    public InventoryReservationRequest(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InventoryReservationRequest{" +
                "productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
