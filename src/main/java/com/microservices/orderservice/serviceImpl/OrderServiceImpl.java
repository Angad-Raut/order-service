package com.microservices.orderservice.serviceImpl;

import com.microservices.orderservice.dto.ItemsDto;
import com.microservices.orderservice.dto.OrderDto;
import com.microservices.orderservice.dto.OrderResponseDto;
import com.microservices.orderservice.exception.InvalidDataException;
import com.microservices.orderservice.model.OrderDetails;
import com.microservices.orderservice.model.OrderItems;
import com.microservices.orderservice.repository.OrderDetailsRepository;
import com.microservices.orderservice.service.OrderService;
import com.microservices.orderservice.utils.Constants;
import com.microservices.orderservice.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private ErrorCode errorCode;

    @Override
    @Transactional
    public String placeOrder(OrderDto orderDto) throws InvalidDataException {
        try{
            OrderDetails orderDetails = OrderDetails.builder()
                    .orderNumber(UUID.randomUUID().toString())
                    .orderItems(orderDto.getItemsDtoList().stream()
                            .map(itemsDto -> mapToDto(itemsDto))
                            .collect(Collectors.toList()))
                    .insertTime(new Date())
                    .updateTime(new Date())
                    .build();
            orderDetailsRepository.save(orderDetails);
            return Constants.ORDER_PLACED;
        }catch(DataIntegrityViolationException e) {
            throw new InvalidDataException(errorCode.DATABASE_CONSTRAINT_VIOLATION_ISSUE_OCCURRED);
        }
    }

    @Override
    public List<OrderResponseDto> getAllOrders() {
        List<OrderResponseDto> list = new ArrayList<OrderResponseDto>();
        Integer count=1;
        for (OrderDetails orderDetails:orderDetailsRepository.findAll()) {
            list.add(OrderResponseDto.builder()
                            .srNo(count)
                            .orderId(orderDetails.getOrderId())
                            .orderNumber(orderDetails.getOrderNumber())
                            .insertTime(Constants.formatedDate(orderDetails.getInsertTime()))
                            .updateTime(Constants.formatedDate(orderDetails.getUpdateTime()))
                            .itemsDtoList(orderDetails.getOrderItems().stream()
                                    .map(orderItems -> mapToItem(orderItems)).collect(Collectors.toList()))
                            .build()
                    );
            count++;
        }
        return list;
    }

    private OrderItems mapToDto(ItemsDto itemsDto) {
        return OrderItems.builder()
                .id(itemsDto.getId())
                .price(itemsDto.getPrice())
                .skuCode(itemsDto.getSkuCode())
                .quantity(itemsDto.getQuantity())
                .build();
    }

    private ItemsDto mapToItem(OrderItems orderItems) {
        return ItemsDto.builder()
                .id(orderItems.getId())
                .skuCode(orderItems.getSkuCode())
                .quantity(orderItems.getQuantity())
                .price(orderItems.getPrice())
                .build();
    }
}
