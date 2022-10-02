package com.microservices.orderservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItems> orderItems;
    private Date insertTime;
    private Date updateTime;
}