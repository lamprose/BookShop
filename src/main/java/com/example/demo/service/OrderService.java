package com.example.demo.service;

import com.example.demo.domain.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface OrderService {
    //1
    String saveOne(Order order);

    //2
    String  deleteOne(String orderId);

    //3
    String updateOrder(Order order);

    //4
    Order findOne(String orderID);

    //5
    List<Order> findAll();
}
