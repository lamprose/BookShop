package com.example.demo.service.impl;

import com.example.demo.domain.Book;
import com.example.demo.domain.Shop;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.ShopRepository;
import com.example.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopServicelmpl implements ShopService {
    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private BookRepository bookRepository;

    //加载所有信息
    @Override
    public List<Shop> findAll() {
        List<Shop> list =shopRepository.findAll();
        return list;
    }

    @Override
    public boolean saveOne(Shop shop){
        boolean flag = false;
        try{
            shopRepository.save(shop);
            flag =true;
        }catch (Exception e){
            System.out.println("保存失败！");
        }
        return flag;
    }

    @Override
    public String updateOne(Shop shop) {
        boolean flag1 = false;
        String ordernum = shop.getOrderNum();
        String id = shop.getId();
        String title = shop.getTitle();
        Integer sum = shop.getSum();
        try {
            shopRepository.updateOne(ordernum,id,title,sum);
            flag1 = true;
        }catch (Exception e){
            System.out.println("update search!");
        }
        return flag1 == true? "success" : "failed";
    }

    @Override
    public String deleteOne(String ordernum) {
        Shop shop = shopRepository.findOneByOrderNum(ordernum);
        if(shop == null){
            return "search failed！";
        }
        boolean flag = false;
        try {
            shopRepository.delete(shop);
            flag =true;
        }catch (Exception e){
            System.out.println("delete faild");
        }
        return flag == true? "success" : "failed";
    }

    @Override
    public List<Book> finduserAll(String id) {
        List<Book> listbook = new ArrayList<>();
        List<Shop> listshop = shopRepository.findAllById(id);
        for (int i = 0; i < listshop.size(); i++) {
            String title = listshop.get(i).getTitle();
            listbook.add(bookRepository.findOneByTitle(title));
        }
        return listbook;
    }

    @Override
    public List<Shop> findAllid(String id){
        List<Shop> list = shopRepository.findAllById(id);
        return list;
    }



}
