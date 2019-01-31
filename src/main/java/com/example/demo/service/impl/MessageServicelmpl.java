package com.example.demo.service.impl;

import com.example.demo.domain.Message;
import com.example.demo.repository.MessageRepository;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class MessageServicelmpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> findAll() {
        List<Message> list =messageRepository.findAll();
        return list;
    }

    @Override
    public boolean saveOne(Message message){
        boolean flag = false;
        try{
            messageRepository.save(message);
            flag =true;
        }catch (Exception e){
            System.out.println("保存失败！");
        }
        return flag;
    }

    /*@Override
    public String updateOne(Message message) {
        boolean flag1 = false;
        String userid = message.getUserid();
        String content = message.getContent();
        java.util.Date date = message.getDate();
        try {
            shopRepository.updateOne(ordernum,id,title,sum);
            flag1 = true;
        }catch (Exception e){
            System.out.println("update search!");
        }
        return flag1 == true? "success" : "failed";
    }*/

    /*@Override
    public String deleteOne(Message message) {
        String username = message.getUserid();
        java.util.Date date = message.getDate();
        Message message1 = messageRepository.findOneByUseridAndDate(username,date);
        if(message1 == null){
            return "search failed！";
        }
        boolean flag = false;
        try {
            messageRepository.delete(message1);
            flag =true;
        }catch (Exception e){
            System.out.println("delete faild");
        }
        return flag == true? "success" : "failed";
    }*/
}
