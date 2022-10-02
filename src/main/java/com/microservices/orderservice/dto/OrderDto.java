package com.microservices.orderservice.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderDto {
    private List<ItemsDto> itemsDtoList=new ArrayList<>();
}
