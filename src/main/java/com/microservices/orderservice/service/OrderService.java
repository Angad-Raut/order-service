package com.microservices.orderservice.service;

import com.microservices.orderservice.dto.OrderDto;
import com.microservices.orderservice.dto.OrderResponseDto;
import com.microservices.orderservice.exception.InvalidDataException;
import com.microservices.orderservice.model.OrderDetails;

import java.util.List;

public interface OrderService {
    String placeOrder(OrderDto orderDto)throws InvalidDataException;
    List<OrderResponseDto> getAllOrders();
}
