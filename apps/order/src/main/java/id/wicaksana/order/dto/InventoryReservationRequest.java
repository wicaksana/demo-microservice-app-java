package id.wicaksana.order.dto;

import jakarta.validation.constraints.Positive;

public record InventoryReservationRequest(
        @Positive
        Integer productId,
        @Positive
        Integer quantity
) {
}
