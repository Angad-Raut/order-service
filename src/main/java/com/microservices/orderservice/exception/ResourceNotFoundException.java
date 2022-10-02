package com.microservices.orderservice.exception;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
