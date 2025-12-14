package id.wicaksana.demo.inventory.dto;

public class InventoryReservationResponse {
    private String status;  // "SUCCESS", "OUT_OF_STOCK"
    private String message;

    public InventoryReservationResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
