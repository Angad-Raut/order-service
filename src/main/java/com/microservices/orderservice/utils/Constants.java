package com.microservices.orderservice.utils;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Constants {
    public static final String ORDER_PLACED="Order placed successfully!!";

    public static final String formatedDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        return format.format(date);
    }
}
