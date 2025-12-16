package id.wicaksana.order.dto;

import jakarta.validation.constraints.NotBlank;

public record InventoryReservationResponse(
        @NotBlank
        String status,
        @NotBlank
        String message
) {
}
