package com.microservices.orderservice.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ItemsDto {
    private Long id;
    private String skuCode;
    private Double price;
    private Integer quantity;
}
