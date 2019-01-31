package com.example.demo.controller;

import com.example.demo.domain.Message;
import com.example.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/message",produces = { "application/json;charset=UTF-8"})
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/findAll")
    public List<Message> findAllmessage (){
        return messageService.findAll();
    }

    @PostMapping("/saveOne")
    public boolean saveOne(@RequestBody Message message) {return messageService.saveOne(message);}

    /*@GetMapping("/deleteOne/{ordernum}")
    public String deleteOne(@RequestBody Message message){
        return messageService.deleteOne(message);
    }*/
}
