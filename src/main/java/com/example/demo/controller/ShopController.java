package com.example.demo.controller;

import com.example.demo.domain.Book;
import com.example.demo.domain.Shop;
import com.example.demo.repository.ShopRepository;
import com.example.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/shop",produces = { "application/json;charset=UTF-8"})
public class ShopController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping("/findAll")
    public List<Shop> findAllshop(){
        return shopService.findAll();
    }

    @GetMapping("/deleteOne/{ordernum}")
    public String deleteOne(@PathVariable("ordernum") String ordernum){
        return shopService.deleteOne(ordernum);
    }

    @PostMapping("/saveOne")
    public  boolean saveOne(@RequestBody Shop shop){
        return shopService.saveOne(shop);
    }

    @PostMapping("/updateOne")
    public String  updateOne(@RequestBody Shop shop){return shopService.updateOne(shop);}

    //查询某一用户的所有书籍
    @GetMapping("/finduserAll/{id}")
    public List<Book> finduserAll(@PathVariable("id") String id){return shopService.finduserAll(id);}

    @GetMapping("findAllid/{id}")
    public List<Shop> findAllid(@PathVariable("id") String id) {return shopService.findAllid(id);}
    /*--------------------*/

    @GetMapping("/exam/{li}")
    public String exam(@PathVariable("li") String li){return li;}

}
