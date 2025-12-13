package id.wicaksana.demo.order.dto;

public class InventoryReservationResponse {
    private String status;
    private String message;

    public InventoryReservationResponse() {
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
