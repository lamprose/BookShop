package com.example.demo.service;

import com.example.demo.domain.Shop;
import com.example.demo.domain.Book;

import java.util.List;

public interface ShopService {
    List<Shop> findAll();

    String updateOne(Shop shop);

    String  deleteOne(String order);

    boolean saveOne(Shop shop);

    /*------搜索某个用户的所有购物车记录------------*/
    List<Book> finduserAll(String id);

    List<Shop> findAllid(String id);
    /*------搜索某个用户的所有购物车记录------------*/
}
