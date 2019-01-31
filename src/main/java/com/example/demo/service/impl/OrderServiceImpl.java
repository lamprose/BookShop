package com.example.demo.service.impl;

import com.example.demo.domain.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String saveOne(Order order) {
        boolean flag =false;
        try{
            orderRepository.save(order);
            flag =true;
        }catch (Exception e){
            System.out.println("保存失败！");
        }

        return flag == true? "success":"failed";
    }

    @Override
    public List<Order> findAll() {
        List<Order> list = orderRepository.findAll();
        return list;
    }

    @Override
    public String deleteOne(String orderId) {
        Order order = orderRepository.findOneByOrderId(orderId);
        if(order == null){
            return "search failed！";
        }
        boolean flag = false;
        try {
            orderRepository.delete(order);
            flag =true;
        }catch (Exception e){
            System.out.println("delete faild");
        }
        return flag == true? "success" : "failed";
    }

    @Override
    public String updateOrder(Order order) {
        boolean flag = false;
        String orderId = order.getOrderId();
        String orderSource = order.getOrderSource();
        String totalPrice = order.getTotalPrice();
        String remark = order.getRemark();
        try {
            orderRepository.updateOrder(orderId,orderSource,totalPrice,remark);
            flag =true;
        }catch (Exception e){
            System.out.println("update search!");
        }
        return flag == true? "success" : "failed";
    }

    @Override
    public Order findOne(String orderId) {
        Order order = orderRepository.findOneByOrderId(orderId);
        return order == null ? null :order;
    }
}
