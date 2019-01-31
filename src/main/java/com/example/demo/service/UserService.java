package com.example.demo.service;
import com.example.demo.domain.User;

import java.util.List;

public interface UserService {
    //实现登陆验证
    User findOneByIdPassword(String id,String password);

    //实现注册功能
    boolean saveOne(User user);

    //实现修改用户信息功能
    String updateOne(User user);

    //实现修改密码功能
    String updateOnePassword(String id,String newPassword);

    List<User> findAll();

    String  deleteOne(String id);

    User findOne(String id);


}
