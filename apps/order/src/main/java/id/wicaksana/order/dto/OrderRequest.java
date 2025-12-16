package id.wicaksana.order.dto;

import jakarta.validation.constraints.Positive;

public record OrderRequest(
        @Positive
        Integer productId,
        @Positive
        Integer quantity
) {
}
