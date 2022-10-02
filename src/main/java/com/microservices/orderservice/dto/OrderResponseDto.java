package com.microservices.orderservice.dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class OrderResponseDto {
    private Integer srNo;
    private Long orderId;
    private String orderNumber;
    private String insertTime;
    private String updateTime;
    private List<ItemsDto> itemsDtoList;
}
