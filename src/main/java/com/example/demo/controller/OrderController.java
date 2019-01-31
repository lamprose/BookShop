package com.example.demo.controller;

import com.example.demo.domain.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order",produces = { "application/json;charset=UTF-8"})
public class OrderController {


    @Autowired
    private OrderService orderService;

    // 保存订单

    @PostMapping("/saveOne")
    public  String saveOne(@RequestBody Order order){
        return orderService.saveOne(order);
    }

    //2
    @GetMapping("/deleteOne/{orderId}")
    public String deleteOne(@PathVariable("orderId") String orderId){
        return orderService.deleteOne(orderId);
    }
    //3
    @PostMapping("/updateOne")
    public String updateOne(@RequestBody Order order){
        return orderService.updateOrder(order);
    }

    //4
    @GetMapping("/findOne/{orderId}")
    public Order findOne (@PathVariable("orderId") String orderId){
        return orderService.findOne(orderId);
    }

    //5
    @GetMapping("/findAll")
    public List<Order> findAllorder (){
        return orderService.findAll() ;
    }
}
