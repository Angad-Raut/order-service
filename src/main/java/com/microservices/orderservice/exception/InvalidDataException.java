package com.microservices.orderservice.exception;

public class InvalidDataException extends Exception {
    public InvalidDataException(String message){
        super(message);
    }
}
