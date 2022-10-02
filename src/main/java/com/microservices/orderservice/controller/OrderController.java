package com.microservices.orderservice.controller;

import com.microservices.orderservice.dto.OrderDto;
import com.microservices.orderservice.dto.OrderResponseDto;
import com.microservices.orderservice.dto.ResponseDto;
import com.microservices.orderservice.exception.InvalidDataException;
import com.microservices.orderservice.service.OrderService;
import com.microservices.orderservice.utils.ErrorHandlerComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ErrorHandlerComponent errorHandler;

    @PostMapping
    @RequestMapping(value = "/placeOrder")
    public ResponseEntity<ResponseDto<String>> placeOrder(@RequestBody OrderDto orderDto,BindingResult result) {
        if(result.hasErrors()) {
            return errorHandler.handleValidationErrors(result);
        }
        try {
            return new ResponseEntity<ResponseDto<String>>(new ResponseDto<String>(
                    orderService.placeOrder(orderDto),null), HttpStatus.CREATED);
        }catch(InvalidDataException e) {
            return new ResponseEntity<ResponseDto<String>>(new ResponseDto<String>(null,
                    errorHandler.getMessageLocal(e.getMessage())),HttpStatus.OK);
        }
    }

    @GetMapping
    @RequestMapping(value = "/getAllOrders")
    public ResponseEntity<ResponseDto<List<OrderResponseDto>>> getAllOrders() {
           return new ResponseEntity<ResponseDto<List<OrderResponseDto>>>(new ResponseDto<List<OrderResponseDto>>(
                   orderService.getAllOrders(),null), HttpStatus.OK);
    }
}
