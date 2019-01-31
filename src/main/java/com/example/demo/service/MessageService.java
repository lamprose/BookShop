package com.example.demo.service;

import com.example.demo.domain.Message;
import com.example.demo.domain.Shop;

import java.util.List;

public interface MessageService {
    List<Message> findAll();

    boolean saveOne(Message message);

    /*String updateOne(Message message);*/

    /*String  deleteOne(Message message);*/
}
