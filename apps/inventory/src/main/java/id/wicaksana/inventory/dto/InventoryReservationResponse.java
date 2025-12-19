package id.wicaksana.inventory.dto;

public class InventoryReservationResponse {
    private String status;  // "SUCCESS", "OUT_OF_STOCK"
    private String message;

    public InventoryReservationResponse() {
    }

    public InventoryReservationResponse(String status, String message) {
        this.status = status;  // SUCCESS, OUT_OF_STOCK, NOT_FOUND
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

    @Override
    public String toString() {
        return "InventoryReservationResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
