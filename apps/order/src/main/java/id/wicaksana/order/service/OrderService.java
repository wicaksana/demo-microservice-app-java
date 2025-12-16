package id.wicaksana.order.service;

import id.wicaksana.order.dto.OrderRequest;
import id.wicaksana.order.dto.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request);
}
