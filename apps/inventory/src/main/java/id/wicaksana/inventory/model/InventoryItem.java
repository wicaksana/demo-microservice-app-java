package id.wicaksana.inventory.model;

import java.io.Serializable;

public class InventoryItem implements Serializable {
    private Integer productId;
    private String name;
    private Integer quantity;

    public InventoryItem() {
    }

    public InventoryItem(Integer productId, String name, Integer quantity) {
        this.productId = productId;
        this.name = name;
        this.quantity = quantity;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
