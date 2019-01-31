package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user",produces = { "application/json;charset=UTF-8"})
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public User getUserByGet(@RequestParam String id){
        return userRepository.findOneById(id);
    }

    @PostMapping("/saveOne")
    public  boolean saveOne(@RequestBody User user){
        return userService.saveOne(user);
    }

    @PostMapping("/updateOne")
    public String  updateOne(@RequestBody User user){return userService.updateOne(user);}

    @GetMapping("/updateOnePassword")
    public String  updateOne(@RequestParam String id,@RequestParam String password){return userService.updateOnePassword(id,password);}

    @GetMapping("/login/user")
    public String getUser(){
        return "1234";
    }

    @GetMapping("/findAll")
    public List<User> findAlluser (){
        return userService.findAll() ;
    }

    @GetMapping("/deleteOne/{id}")
    public String deleteOne(@PathVariable("id") String id){
        return userService.deleteOne(id);
    }

    @GetMapping("/findOne{id}")
    public User findOne(@PathVariable("id") String id){return userService.findOne(id);}

}
