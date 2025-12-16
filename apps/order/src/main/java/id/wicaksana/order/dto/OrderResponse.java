package id.wicaksana.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record OrderResponse(
        @Positive long orderId,
        @Positive Integer productId,
        @Positive Integer quantity,
        @NotBlank String status
) {
}
