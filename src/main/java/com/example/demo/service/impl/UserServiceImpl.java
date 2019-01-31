package com.example.demo.service.impl;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOneByIdPassword(String id, String password){
        return userRepository.findOneById(id);
    }

    @Override
    public boolean saveOne(User user){
        boolean flag =false;
        try{
            userRepository.save(user);
            flag =true;
        }catch (Exception e){
            System.out.println("保存失败！");
        }
        return flag;
    }

    @Override
    public String updateOne(User user) {
        boolean flag1 = false;
        String id = user.getId();
        String name = user.getName();
        String password = user.getPassword();
        String phone = user.getPhone();
        String address = user.getAddress();
        Integer sex = user.getSex();
        Integer flag = user.getFlag();
        try {
            userRepository.updateOne(id,name,password,phone,address,sex,flag);
            flag1 = true;
        }catch (Exception e){
            System.out.println("update search!");
        }
        return flag1 == true? "success" : "failed";
    }

    @Override
    public String updateOnePassword(String id,String newPassword){
        boolean flag=false;
        try {
            userRepository.updateOnePassword(id,newPassword);
            flag=true;
        }catch (Exception e){
            System.out.println("update search!");
        }
        return flag == true? "success" : "failed";
    }

    @Override
    public String deleteOne(String id) {
        User user = userRepository.findOneById(id);
        if(user == null){
            return "search failed！";
        }
        boolean flag = false;
        try {
            userRepository.delete(user);
            flag =true;
        }catch (Exception e){
            System.out.println("delete faild");
        }
        return flag == true? "success" : "failed";
    }

    @Override
    public List<User> findAll() {
        List<User> list = userRepository.findAll();
        return list;
    }

    @Override
    public User findOne(String id){
        User user = userRepository.findOneById(id);
        return user;
    }
}
